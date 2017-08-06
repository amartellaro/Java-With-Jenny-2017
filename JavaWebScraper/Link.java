package JavaWebScraper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Link
{
	private String siteUrl;
	private String displayText;
	private String url;
	private String title;
	private String cssClass;

	public Link()
	{
	}

	public Link(String siteUrl,  String url, String displayText, String title, String cssClass)
	{
		this.siteUrl = siteUrl;
		this.displayText = displayText;
		this.url = url;
		this.title = title;
		this.cssClass = cssClass;
	}

	@Override
	public String toString() 
	{
		return displayText 
				+ (title.equals("") ? "" : " (" + title + ")") 
				+ " - " + getFullUrl() 
				+ " " + (cssClass.equals("") ? "" : " class=" + cssClass);
	}
	
	public String getSiteUrl()
	{
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl)
	{
		this.siteUrl = siteUrl;
	}

	public String getDisplayText()
	{
		return displayText;
	}

	public void setDisplayText(String displayText)
	{
		this.displayText = displayText;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getFullUrl()
	{
		if (this.url.startsWith("http")) {
			return this.url;
		} 
		return this.siteUrl + this.url;
	}
	/** If full url is http://www.google.com/ gets the google.com part; else null */
	public String getDomain()
	{
		Pattern p = Pattern.compile("htt.*?://(.*?)/");
		Matcher m = p.matcher(getFullUrl());
		if (m.find()) {
			return m.group(1);
		} else {
			return null;
		}
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCssClass()
	{
		return cssClass;
	}

	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}


}