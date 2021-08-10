package com.site_detector.site.controllers;

import com.site_detector.models.Website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

@Controller
public class CheckWebsiteController {
    @RequestMapping(value = "/saveWebsite", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Website site) {
        String url = formatUrl(site.getUrl());
        site.setSiteStatus(getWebsiteStatus(url));
        Website statusWebsite = new Website(url, site.getSiteStatus());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("website_information");
        modelAndView.addObject("statusWebsite", statusWebsite);
        return modelAndView;
    }

    private String formatUrl(String website) {
        String formattedWebsite = "http://" + website;
        return formattedWebsite;
    }
    private String getWebsiteStatus(String formattedWebsite) {
        String result = "";
        try {
            URL urlObj = new URL(formattedWebsite);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2 || responseCodeCategory != 3) {
                result = "DOWN";
            }
            if (responseCodeCategory == 2 || responseCodeCategory == 3) {
                result = "UP";
            }
        } catch (MalformedURLException e) {
            result = "Malformed URL>";
        }
        catch (UnknownHostException e) {
            result = "Unknown Host";
        } 
        catch (IOException e) {
            result = "Error";
            e.printStackTrace();
        }

        return result;
    }
}
