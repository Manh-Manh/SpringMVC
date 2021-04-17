package com.manhdn.taglib;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;

public class PaginationTaglib extends SimpleTagSupport {
	private String uri; // Link
	private int page = 1;// Trang so
	private int count;// Tổng số bản
	private int pageSize = AppConstants.PAGE_SIZE;//
	private int offset = 10;
	private int steps = 10;
	private int max = 10;
	private String type = "";
	private String previous = "Previous";
	private String next = "Next";

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}

	@Override
	public void doTag() throws JspException {
		Writer out = getWriter();
		int pageNumber = (count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1));
		String classs = "";
		if (!FunctionCommon.isEmpty(type)) {
			classs = type + "-link";
//			uri = "#";
		}
		try {
			out.write("<nav>");
			out.write("<ul class=\"\">");
			if (page < steps)
				out.write(constructLink(1, previous, "disabled " + classs, true, false));
			else
				out.write(constructLink(steps * (page / steps - 1) + 1, previous, null, false, false));

			if (pageNumber <= max) {
				for (int itr = 1; itr <= pageNumber; itr++) {
					if (page == itr)
						out.write(constructLink(itr, "" + itr, classs, true, true));
					else
						out.write(constructLink(itr, "" + itr, " " + classs, false, false));
				}
			} else if (pageNumber > max) {
				
				int k = page / steps;
				for (int itr = 1; itr <= max; itr++) {
					if ((steps * k + itr) <= pageNumber) {
						if (page == steps * k + itr)
							out.write(constructLink((steps * k + itr), "" + (steps * k + itr), classs, true, true));
						else
							out.write(constructLink((steps * k + itr), "" + (steps * k + itr), classs, false, false));
					}
				}
				
			}
			if (pageNumber <= max * (page / max + 1))
				out.write(constructLink(steps * (page / steps + 1) + 1, next, "disabled  " + classs, true, false));
			else
				out.write(constructLink(steps * (page / steps + 1) + 1, next, " " + classs, false, false));
			out.write("</ul>");
			out.write("</nav>");
		} catch (java.io.IOException ex) {
			throw new JspException("Error in Paginator tag", ex);
		}
	}

	private String constructLink(int page, String text, String className, boolean disabled, boolean isActive) {
		StringBuilder link = new StringBuilder("<li");
		if (className != null) {
			link.append(" class=\"");
			link.append(className);
			link.append("\"");
			link.append(" value=\"" + page + "\"");
		}
		if (disabled || !FunctionCommon.isEmpty(this.type))
			link.append(">").append("<a class= \" page-num "+ (isActive?" active ":"") +(disabled?" disable ":"") + "\" href=\"#\">" + text + "</a></li>");
		else
			link.append(">").append("<a class= \" page-num " + (isActive?" active ":"") + "\" href=\"" + uri + "?page=" + page + "\">"
					+ text + "</a></li>");
		return link.toString();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
