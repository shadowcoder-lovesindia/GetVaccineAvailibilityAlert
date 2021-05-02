package com.vaccine.getavailibility.model;



import java.util.List;
import java.util.Map;

public class MailMessageRequest {

    private String from;
    private String mailTo;
    private String[] mailCC;
    public String[] getMailCC() {
		return mailCC;
	}

	public void setMailCC(String[] mailCC) {
		this.mailCC = mailCC;
	}

    private List<Object> attachments;
    private Map<String, Object> props;
    
   
 
	public MailMessageRequest() {}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	

	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}

}