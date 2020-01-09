package com.rahul.Login;

public abstract class NavigationPage {
	 private String title;
	    String includeUri;
	    public void setTitle(String title) {
			this.title = title;
		}

		public void setIncludeUri(String includeUri) {
			this.includeUri = includeUri;
		}

		public void setSubTitle(String subTitle) {
			this.subTitle = subTitle;
		}

		public void setData(Object data) {
			this.data = data;
		}
		private String subTitle;
	    private Object data;
	     
	    public NavigationPage(String title, String subTitle, String includeUri, Object data) {
	        super();
	        this.title = title;
	        this.subTitle = subTitle;
	        this.includeUri = includeUri;
	        this.data = data;
	    }
	 
	    public abstract boolean isSelected();
	 
	    public String getTitle() {
	        return title;
	    }
	    public String getSubTitle() {
	        return subTitle;
	    }
	    public String getIncludeUri() {
	        return includeUri;
	    }
	    public Object getData() {
	        return data;
	    }
}
