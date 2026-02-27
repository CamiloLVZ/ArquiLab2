package com.arquitectura.htmlBuilder;

public class HTMLBuilder{

    private String head;
    private String header;
    private String h1;

    public HTMLBuilder head(String texto){
        this.head = "\n\t<head> " + texto + " </head>";
        return this;
    }

    public HTMLBuilder header(String texto){
        this.header = "\n\t\t<header> " + texto + " </header>";
        return this;
    }

    public HTMLBuilder h1(String texto){
        this.h1 = "\n\t\t<h1> " + texto + " </h1>";
        return this;
    }

    public String build(){
        return "<html>" + head + "\n\t<body>" + header + h1 + "\n\t</body>\n</html>";
    }

}