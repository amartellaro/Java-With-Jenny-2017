package JavaWebScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fetches a web page of your choosing and scrapes out pieces of it, such as links.
 * This will be unreliable if the web page uses single quotes or no quotes around
 * their tag attributes; it matches href="foo" but not href=foo or href='foo' so
 * some pages may not work as well as others.
 */
public class WebScraper 
{
    /** From <a href="foo">bar</a> returns the foo part */
    String getHrefFromLinkTag(String atag)
    {
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(atag);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    /** From <a href="foo">bar</a> returns the bar part */
    String getDisplayTextFromLinkTag(String atag)
    {
        Pattern pattern = Pattern.compile(">(.*?)<");
        Matcher matcher = pattern.matcher(atag);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }		
    }

    /** From <a href="foo" title="baz">bar</a> returns the baz part */
    String getTitleFromLinkTag(String atag)
    {
        Pattern pattern = Pattern.compile("title=\"(.*?)\"");
        Matcher matcher = pattern.matcher(atag);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }		
    }

    /** From <a href="foo" class="cssname">bar</a> returns the cssname part */
    String getCssClassFromLinkTag(String atag)
    {
        Pattern pattern = Pattern.compile("class=\"(.*?)\"");
        Matcher matcher = pattern.matcher(atag);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }		
    }

    /**
     * Examines a chunk of html source and uses pattern-matching to identify the links
     * within it; may not always be able to find every link, depending on how well the pattern
     * matches the exact web page.
     * @param htmlSource
     * @return
     */
    List<Link> scrapeForLinks(String siteUrl, String htmlSource)
    {
        List<Link> links = new ArrayList<Link>();

        // Sample: This is <a href="http://www.google.com/" title="Google">a google link</a> in html.

        // First layer of regular-expression pattern matching will look for an
        // opening <a> tag followed by a closing </a> tag.  It will capture
        // everything in between including the opening/closing tags.
        Pattern pattern = Pattern.compile("(<a .*?</a>)");
        Matcher matcher = pattern.matcher(htmlSource);
        while (matcher.find()) {
            String atag = matcher.group();
            // Next we'll do a deeper pass to try to identify the parts within that.
            String href = getHrefFromLinkTag(atag);
            String displayText = getDisplayTextFromLinkTag(atag);
            String title = getTitleFromLinkTag(atag);
            String cssClass = getCssClassFromLinkTag(atag);

            Link link = new Link(siteUrl, href, displayText, title, cssClass);
            links.add(link);
        }
        return links;
    }

    /**
     * Produces a report with one link per line
     * @param links
     * @return
     */
    String report(List<Link> links)
    {
        String report = "";
        for (Link link : links) {
            report += link; // uses the toString() method on the Link class.
            report += "\n";
        }
        report += "\n\n";
        report += "There are " + links.size() + " links found in the page.\n\n";
        report += "Here are the domains and the percentages of each (if above 0.25%).\n";
        report += reportOnTopDomains(links);
        return report;
    }

    private String reportOnTopDomains(List<Link> links)
    {
        String report = "";
        // Figure out how many times each domain was referenced.
        int total = 0;
        Map<String, Integer> domainCounts = new HashMap<String, Integer>();
        for (Link link : links) {
            String domain = link.getDomain();
            if (domain == null) {
                continue; // this one was invalid so skip it and move on to the next link
            }
            Integer count = domainCounts.get(domain);
            if (count == null) {
                count = 0;
            }
            domainCounts.put(domain, count + 1);
            total++;
        }

        // Print any domains that were referenced in at least 0.25% (a quarter of a percent)
        // of the links on the page.
        DecimalFormat df = new DecimalFormat("0.#");
        for (String key : domainCounts.keySet()) {
            int count = domainCounts.get(key);
            float percent = (float) count * 100f / (float) total;
            if (percent >= 0.25f) {
                report += "Domain " + key + " was " + df.format(percent) + "% of total links.\n";
            }
        }
        return report;
    }

    /**
     * Fetches a web page at a given url and reports on what we found scraping through it.
     * @param url A valid http://whatever.com/ style url address
     * @throws MalformedURLException If the url passed in has typos or isn't actually a URL
     * @throws IOException If the network connection dies while we're trying to read the web page
     */
    public void fetchAndReport(String url) throws MalformedURLException, IOException
    {
        String webPageSource = fetch(url);
        List<Link> links = scrapeForLinks(url, webPageSource);
        String report = report(links);

        System.out.println("Fetching " + url + ", we see the following links: ");
        System.out.println(report);
    }

    /**
     * Opens a connection to a web server, reads the HTML source for that page, and 
     * returns the source as a string.
     * @param url A valid http://whatever.com/ style url address
     * @return String containing the web page's source code
     * @throws MalformedURLException If the url passed in has typos or isn't actually a URL
     * @throws IOException If the network connection dies while we're trying to read the web page
     */
    public String fetch(String url) throws MalformedURLException, IOException
    {
        String line = null;
        String page = "";

        URL webConnection = new URL(url);

        // read the stream of data line by line and append it to a String.
        BufferedReader reader = new BufferedReader(new InputStreamReader(webConnection.openStream()));
        while ( (line = reader.readLine()) != null) {
            page += line;
        }
        reader.close();

        // and return the entire string.
        return page;
    }

    /**
     * Takes a url interactively and runs the web scraper on it, producing a report.
     * @param args Ignored
     * @throws MalformedURLException If the url passed in has typos or isn't actually a URL
     * @throws IOException If the network connection dies while we're trying to read the web page
     */
    public static void main(String[] args)
    throws MalformedURLException, IOException
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What url should we scrape?");
        System.out.println("(default: https://en.wikipedia.org/wiki/Java_(programming_language) ");
        String url = keyboard.nextLine().trim();
        if (url.equals("")) {
            url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        }

        WebScraper scraper = new WebScraper();
        scraper.fetchAndReport(url);

        keyboard.close();
    }

}
