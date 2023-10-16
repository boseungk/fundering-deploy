package com.theocean.fundering.global.utils;


import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentNodeRendererContext;
import org.commonmark.renderer.text.TextContentRenderer;


public class HTMLUtils {
    public static String markdownToHTML(String md){
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node content = parser.parse(md);
        return renderer.render(content);
    }

}
