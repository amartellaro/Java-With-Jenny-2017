package JavaWebScraper;

import java.util.List;

public class WebScraperTest
{
	private static void shouldBeEqual(String actual, String expected)
	{
		if (actual == null && expected == null)
		{
			return; // they match, that's fine.
		}
		if (actual == null || !actual.equals(expected))
		{
			System.err.println("Error: Expected " + expected + " but got " + actual);
		}
	}

	public static void main(String[] args)
	{
		WebScraper scraper = new WebScraper();
		
		// First let's run some tests to ensure we're extracting pieces of links accurately.
		shouldBeEqual(scraper.getHrefFromLinkTag("<a href=\"http://www.google.com/\">Google</a>"),
				"http://www.google.com/");
		shouldBeEqual(scraper.getHrefFromLinkTag("<a href=\"http://www.google.com/\" title=\"Foo\">Google</a>"),
				"http://www.google.com/");

		shouldBeEqual(scraper.getTitleFromLinkTag("<a href=\"http://www.google.com/\">Google</a>"),
				"");
		shouldBeEqual(scraper.getTitleFromLinkTag("<a href=\"http://www.google.com/\" title=\"Foo\">Google</a>"), 
				"Foo");

		shouldBeEqual(scraper.getDisplayTextFromLinkTag("<a href=\"http://www.google.com/\">Google</a>"), 
				"Google");
		shouldBeEqual(scraper.getDisplayTextFromLinkTag("<a href=\"http://www.google.com/\" title=\"Foo\">Google</a>"),
				"Google");

		shouldBeEqual(scraper.getCssClassFromLinkTag("<a href=\"http://www.google.com/\">Google</a>"), 
				"");
		shouldBeEqual(scraper.getCssClassFromLinkTag("<a href=\"http://www.google.com/\" class=\"normal\" title=\"Foo\">Google</a>"),
				"normal");

		// Now let's send some mock html and confirm we're extracting all 4 links from it correctly.
		String mockHtmlSource = "<td colspan=\"2\" style=\"text-align:center\">"
				+ "<a href=\"/wiki/Ada_(programming_language)\" title=\"Ada (programming language)\">Ada "
				+ "2005</a>, <a href=\"/wiki/BeanShell\" title=\"BeanShell\">BeanShell</a>, "
				+ "<a href=\"/wiki/C_Sharp_(programming_language)\" "
				+ "title=\"C Sharp (programming language)\">C#</a>, "
				+ "<a href=\"/wiki/Clojure\" title=\"Clojure\">Clojure</a>,";
		
		List<Link> links = scraper.scrapeForLinks("http://www.wikipedia.org", mockHtmlSource);
		if (links.size() != 4) {
			System.err.println("Wrong number of links returned from mock html source");
		}

		// Now that the tests have (presumably) passed, let's print the results and make sure 
		// they look right to the human eye.
		System.out.println("Got the following links:");
		for (Link link : links) {
			System.out.println(link);
		}
	}

}
