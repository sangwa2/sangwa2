/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate.ajaxrest;

import com.codeguru.g_ultimate.diagrams.Srv_dictionary;
import com.codeguru.g_ultimate.files.db.Model;
import com.codeguru.g_ultimate.models.Mdl_structure;

import com.codeguru.g_ultimate.service.Srv_unit;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codeguru.g_ultimate.files.db.Srv_db_maker;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.xml.ws.spi.http.HttpExchange;
import static jdk.nashorn.internal.objects.NativeJava.type;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("api/files")
public class Files_rest {

    @Autowired
    Srv_db_maker srv_db_maker;
    @Autowired
    Srv_dictionary srv_dictionary;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "make_db/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> get_db(@PathVariable("structure") int structure) {
        try {
            System.out.println("got the request: " + structure);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(srv_db_maker.struture_tuple(structure), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error rest: " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "make_dictionary_diag/{structure}", method = RequestMethod.GET, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> get_dictionary(@PathVariable("structure") int structure) {
        try {
            srv_dictionary.dictionary(structure);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(srv_dictionary.dictionary(structure), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error res in " + Files_rest.class.getName() + " " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "getpath_files", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> get_dbs(HttpServletResponse response ) throws IOException {
        List<String> my_files = new ArrayList();
        String path = servletContext.getRealPath("/") + "apps/";
        File the_folder = new File(path);
        File[] listOfFiles = the_folder.listFiles();
        for (File listOfFile : listOfFiles) {
            my_files.add(path + "." + listOfFile.getName());
        }
//        download(response, type);
        return new ResponseEntity<>(my_files, HttpStatus.OK);
    }

    private void download(HttpServletResponse response, String type) throws IOException {
        File file = null;
        String path = servletContext.getRealPath("/") + "apps/";
        String INTERNAL_FILE = path;
        String EXTERNAL_FILE_PATH = "C:/mytemp/SpringMVCHibernateManyToManyCRUDExample.zip";
        if (type.equalsIgnoreCase("internal")) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            file = new File(classloader.getResource(INTERNAL_FILE).getFile());
        } else {
            file = new File(path);
        }

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
