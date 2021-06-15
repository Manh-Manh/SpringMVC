package com.manhdn.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.manhdn.FunctionCommon;

public class Crawler {
	public Document doc;
	List<String> listDomain = new ArrayList<String>();
	List<String> product_key = new ArrayList<String>();
	List<String> image_key = new ArrayList<String>();
	List<String> price_key = new ArrayList<String>();
	List<String> unit_price_key = new ArrayList<String>();
	List<String> dis_price_key = new ArrayList<String>();
	List<String> key_value = new ArrayList<String>();
	List<String> name_key = new ArrayList<String>();
	private String Q="";
	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

	public Crawler() {
		product_key.add("frame_center");
		product_key.add("product");
		product_key.add("main_container");
		product_key.add("container");
		// name
//		name_key.add("name");
		name_key.add("product_name");
		name_key.add("product-name");
		name_key.add("product-name-dk");
		name_key.add("product-title");
		name_key.add("h1[itemprop=name]");
		// image
		
		image_key.add("frame_left");
		image_key.add("thumbnail-item");
		image_key.add("main-img");
		image_key.add("product-img-box");
		image_key.add("product-img");
		image_key.add("product-images");
		image_key.add("img");
		image_key.add("thumbs");
		image_key.add("box_left");
		image_key.add("images");
		image_key.add("active-product-gallery");
		// unit price
		unit_price_key.add("meta[itemprop=highPrice]");
		unit_price_key.add("price_old");
		unit_price_key.add("box-price-old");
		unit_price_key.add("old_price");
		unit_price_key.add("original-price");
		unit_price_key.add("del");
		unit_price_key.add("s");
		// discount price
		dis_price_key.add("meta[itemprop=lowPrice]");
		dis_price_key.add("price_current");
		dis_price_key.add("current-price");
		dis_price_key.add("box-price-present");
		dis_price_key.add("ins");
		// key_value
		key_value.add("content");
		key_value.add("data-price");
	}

	static {
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}
	/**
	 * Lay du lieu tren google search
	 * @param query
	 * @return json
	 */
	public String getDataFromGoogle(String query) {
		this.Q = query;
		List<String> listUrl = new ArrayList<String>();
//	    List<String> result = new ArrayList<String>();	
		List<String> listUrlDetail = new ArrayList<String>();
		String result = null;
		String request = "https://www.google.com/search?q=" + query + "&num=20";
		listUrl.add(request);
		System.out.println("Sending request..." + request);
		try {
			while (listUrl.size() < 4) {
				listUrl.add(getListNextPage(listUrl.get(listUrl.size() - 1)));
			}
			for (String url : listUrl) {
				for (String sss : getListUrlDetail(url)) {
					if (listUrlDetail.size() < 10) {
						listUrlDetail.add(sss);
					}
				}
			}

		} catch (Exception e) {
//	        e.printStackTrace();
		}
		result = "[ ";
		if (listUrlDetail != null && listUrlDetail.size() > 0) {

			for (String s : listUrlDetail) {
				try {
					String ss = this.getProductDetail(s);
					if (ss != null && ss.length() > 0) {
						result = result + ss + ",";
					}
				} catch (Exception e) {
				}
			}
		}
		result = result.substring(0, result.length() - 1);
		result = result + "]";
		return result;
	}

	public String getDomainName(String url) {

		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;

	}

	public String getUrlResult(String url) {
		String link = "";
		String[] str1 = url.split("=");
		String str2 = str1[1];
		link = str2.split("&")[0];
		return link;

	}

	/**
	 * Lay trang ket qua tiep theo
	 * 
	 * @param url
	 * @return
	 */
	public String getListNextPage(String url) {
//			String result =new ArrayList<String>();
		String nextUrl = "";
		try {
			// need http protocol, set this as a Google bot agent :)
			Document d = Jsoup.connect(url).timeout(3000).get();
			// get all links
			Element p = d.getElementById("pnnext");
			nextUrl = "https://www.google.com/" + p.attr("href");
			return nextUrl;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return nextUrl;
	}

	/**
	 * Lay cac url ket qua thong qua url trang
	 * 
	 * @param urlPage
	 * @return
	 */
	public List<String> getListUrlDetail(String urlPage) {
		List<String> result = new ArrayList<String>();
		try {

			// need http protocol, set this as a Google bot agent :)
			Document doc = Jsoup.connect(urlPage)
					.userAgent("Mozilla/5.0 " + "(compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.timeout(3000).get();
			// get all links

			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String temp = link.attr("href");
				if (temp.startsWith("/url?q=")) {

					if (!listDomain.contains(this.getDomainName(temp))) {
						result.add(getUrlResult(temp));
						listDomain.add(getDomainName(temp));
//							return new ArrayList<String>();
					}

				}

			}

		} catch (IOException e) {
//				e.printStackTrace();
		}

		return result;
	}

	/**
	 * Lay thong cua san pham qua url
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String getProductDetail(String url) throws IOException {
		Document d;
		try {
			d = Jsoup.connect(url).timeout(3000).get();
		} catch (Exception e) {
			return null;
		}
		String domain = getDomainName(url);
		String img_link = null;
		String unitPriceString = null;
		String disPriceString = null;
		String productName = null;
		Element product = null;
		for (String s : product_key) {
			if (canGet(img_link, unitPriceString, disPriceString)) {
				break;
			}
			try {
				product = d.getElementsByClass(s).get(0);
			} catch (Exception e) {
//					e.printStackTrace();
			}
			if (null != product) {
				// Lấy ảnh
				Element image = null;
				for (String s2 : image_key) {
					if (canGet(img_link, unitPriceString, disPriceString)) {
						break;
					}
					
//						if(null != d.getElementsByClass(s2) ){
//							continue;
//						}
					try {
						image = d.getElementsByClass(s2).get(0);
					} catch (Exception e) {
//							e.printStackTrace();
						continue;
					}
					if (null != image) {
						try {
							if (FunctionCommon.isEmpty(img_link)) {
								img_link = image.select("img").get(0).attr("src");
								if (FunctionCommon.isEmpty(image_key)) {
									img_link = image.select("img").get(0).attr("data-src");
								}
							}
						} catch (Exception e) {
//								e.getStackTrace();
						}
						// Lay Ten
						Element name = null;
						for (String s3 : name_key) {
							if (!FunctionCommon.isEmpty(productName)) {
								break;
							}
							try {
								name = d.select(s3).first();
								if (name == null) {
									name = d.getElementsByClass(s3).get(0);
								}
							} catch (Exception e) {
							}
							if (null != name) {
								for (String att : key_value) {
									productName = getHtmlVal(name);
									if (null != productName && productName.length() > 0) {
										break;
									}

								}
							}
						}
						// Lấy giá goc
						Element unitPrice = null;
						for (String s3 : unit_price_key) {
							if (!FunctionCommon.isEmpty(unitPriceString)) {
								break;
							}
							try {
								unitPrice = d.select(s3).first();
								if (unitPrice == null) {
									unitPrice = d.getElementsByClass(s3).get(0);
								}
							} catch (Exception e) {
							}
							if (null != unitPrice) {
								for (String att : key_value) {
									unitPriceString = findAttribute(unitPrice, att);
									if (null != unitPriceString && unitPriceString.length() > 0) {
										break;
									}
								}
								if (null == unitPriceString || unitPriceString.length() == 0) {
									unitPriceString = getHtmlPrice(unitPrice);
									if (null != unitPriceString && unitPriceString.length() > 0) {
//										unitPriceString = unitPriceString.replaceAll("\\.", "");
//										unitPriceString = unitPriceString.replaceAll(",", "");
//										unitPriceString = unitPriceString.replaceAll("₫", "");
//										unitPriceString = unitPriceString.replaceAll("VND", "");
//										unitPriceString = unitPriceString.replaceAll("&nbsp;", "");
//										unitPriceString = unitPriceString.replaceAll(" ", "");
//										unitPriceString = unitPriceString.trim();
										String str = "";
										for (int i = 0; i < unitPriceString.length(); i++) {
								            if (Character.isDigit(unitPriceString.charAt(i))) {
								                str += unitPriceString.charAt(i);
								            //   System.out.print(sz.charAt(i));
								            }
								        }
										unitPriceString = str;
										break;
									}
								}
							}
						}
						// Lấy giá giam
						Element disPrice = null;
						for (String s3 : dis_price_key) {
							if (!FunctionCommon.isEmpty(disPriceString)) {
								break;
							}
							try {
								disPrice = d.select(s3).first();
								if (disPrice == null) {
									disPrice = d.getElementsByClass(s3).get(0);
								}
							} catch (Exception e) {
							}
							if (null != disPrice) {
								for (String att : key_value) {
									disPriceString = findAttribute(disPrice, att);
									if (null != disPriceString && disPriceString.length() > 0) {
										break;
									}

								}
								if (null == disPriceString || disPriceString.length() == 0) {
									disPriceString = getHtmlPrice(disPrice);
									if (null != disPriceString && disPriceString.length() > 0) {
//										disPriceString = disPriceString.replaceAll("\\.", "");
//										disPriceString = disPriceString.replaceAll(",", "");
//										disPriceString = disPriceString.replaceAll("₫", "");
//										disPriceString = disPriceString.replaceAll("VND", "");
//										disPriceString = disPriceString.replaceAll(" ", "");
										String str = "";
										for (int i = 0; i < disPriceString.length(); i++) {
								            if (Character.isDigit(disPriceString.charAt(i))) {
								                str += disPriceString.charAt(i);
								            //   System.out.print(sz.charAt(i));
								            }
								        }
										disPriceString = str;
										break;
									}
								}

							}

						}

					}
					if (canGet(img_link, unitPriceString, disPriceString)) {
						break;
					}
				} // end image

			}

			if (canGet(img_link, unitPriceString, disPriceString)) {
				break;
			}
		}

		if (!canGet(img_link, unitPriceString, disPriceString)) {
			return null;
		}
//			StringBuilder result = new StringBuilder();
		String result = "";
		result += "{";
		result += "\"url\":" + "\"" + (url != null ? url : "") + "\"";
		result += ",";
		result += "\"productName\":" + "\"" + (productName != null ? productName : this.Q) + "\"";
		result += ",";
		result += "\"img_link\":" + "\"" + (img_link != null ? img_link : "") + "\"";
		result += ",";
		result += "\"domain\":" + "\"" + (domain != null ? domain : "") + "\"";
		result += ",";
		result += "\"unitPrice\":" + "\"" + (unitPriceString != null ? unitPriceString : "0") + "\"";
		result += ",";
		result += "\"discountPrice\":" + "\"" + (disPriceString != null ? disPriceString : "0") + "\"";
		result += "}";
		return result;

	}
	

	private String getHtmlVal(Element name) {
		if (name.childrenSize() == 0) {
			return name.html();
		} else {
			for (Element e : name.children()) {
				String s = getHtmlVal(e);
				if (s != null && s.length() > 0) {
					return s;
				}
			}
			return null;
		}

	}

	/**
	 * Lay gia tri trong element Gia
	 * 
	 * @param name
	 * @return
	 */
	private String getHtmlPrice(Element name) {
		String result = "";
		if (name.childrenSize() == 0) {
			return name.html();
		}
//			else {
//				for (Element e : name.children()) {
//					result += getHtmlPrice(e);
//				}
//
//			}
		while (name.childrenSize() > 0) {
			result = getHtmlPrice(name.children().last()) + result;
			name.children().last().remove();
		}
		return (name.html() + result);
	}

	private boolean canGet(String img_link, String unitPrice, String disPrice) {
		if (FunctionCommon.isEmpty(image_key)) {
			return false;
		}
		if (!FunctionCommon.isEmpty(unitPrice) || !FunctionCommon.isEmpty(disPrice)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param parent
	 * @param attr
	 * @return Lay gia tri cua thuoc tinh trong nhom the
	 */
	public String findAttribute(Element parent, String attr) {
		if (parent.childrenSize() == 0) {
			return parent.attr(attr);
		} else {
			for (Element e : parent.children()) {
				String s = findAttribute(e, attr);
				if (s != null && s.length() > 0) {
					return s;
				}
			}
			return null;
		}

	}
}
