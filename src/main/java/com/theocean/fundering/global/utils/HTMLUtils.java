package com.theocean.fundering.global.utils;


import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class HTMLUtils {
    public String markdownToHTML(String md){
        Parser parser = Parser.builder().build();
        Node content = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(content);
    }
}
