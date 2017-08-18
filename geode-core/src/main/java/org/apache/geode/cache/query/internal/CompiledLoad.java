package org.apache.geode.cache.query.internal;


/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheClosedException;
import org.apache.geode.cache.EntryDestroyedException;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.query.AmbiguousNameException;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.Index;
import org.apache.geode.cache.query.NameNotFoundException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvalidException;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.RegionNotFoundException;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.Struct;
import org.apache.geode.cache.query.TypeMismatchException;
import org.apache.geode.cache.query.internal.index.AbstractIndex;
import org.apache.geode.cache.query.internal.index.PartitionedIndex;
import org.apache.geode.cache.query.internal.types.ObjectTypeImpl;
import org.apache.geode.cache.query.internal.types.StructTypeImpl;
import org.apache.geode.cache.query.internal.types.TypeUtils;
import org.apache.geode.cache.query.types.CollectionType;
import org.apache.geode.cache.query.types.ObjectType;
import org.apache.geode.cache.query.types.StructType;
import org.apache.geode.internal.i18n.LocalizedStrings;
import org.apache.geode.internal.logging.LogService;
import org.apache.geode.pdx.PdxInstance;
import org.apache.geode.pdx.internal.PdxString;
import org.apache.geode.vector.Vector;
import org.apache.logging.log4j.Logger;
import java.util.Map;
//import entagged.audioformats.*; //Use a jar program!
import java.util.LinkedList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.geode.cache.query.internal.Portfolio;


import org.apache.geode.cache.query.internal.Mp3Obj;

////
/**
 * Created by dylan on 6/25/17.
 */
public class CompiledLoad extends AbstractCompiledValue{
    private CompiledLiteral filePath;
    private CompiledLiteral delim;
    private CompiledLiteral vLim;
    private CompiledRegion region;
    //private CompiledID key;
    private CompiledLiteral className;

    final Logger logger = LogService.getLogger();
    private Vector item = null;
    //private Mp3Obj thing = null;

    public CompiledLoad(CompiledLiteral className, CompiledLiteral filePath, CompiledLiteral delim, CompiledLiteral vLim, CompiledRegion region){
        this.filePath = filePath;
        this.region = region;
        this.delim = delim;
        this.vLim = vLim;
        this.className = className;
        //this.key = key;
    }
        //Set up a collection interface called LoadResults later
    public Object evaluate(ExecutionContext context) throws FunctionDomainException, TypeMismatchException,
            NameResolutionException, QueryInvocationTargetException {
        Object duh = null;
        logger.info("----WITHIN THE EVALUATE OF LOAD!!!!--i --");
        LinkedList <String []> list;// _______COMMENTED____ = new LinkedList<String []> ();

        //This will extract the string within filePath....apparently PdxString is the middleman for that.
        String path = this.filePath.getSavedPdxString().toString();

        try{
            //For scaling up:
            //LinkedList <mp3Obj> list = new LinkedList<mp3Obj>();
            //LinkedList <mp3Obj> taken = new LinkedList<String []>();
            //If it's current directory, it can just be ./


            list = readCSV(delim.getSavedPdxString().toString(), vLim.getSavedPdxString().toString(), path);


            loadRegion(list,context.getCache().getRegion(this.region.getRegionPath()), this.className.getSavedPdxString().toString());

        }//end of try
        catch (Exception e) {
            logger.error("Issue occured in evaluate " + e.getMessage());
            e.printStackTrace();
        }


        return duh;
    }


    public LinkedList <String []> readCSV(String delim, String vEnd,  String readSrc) {
        LinkedList<String[]> list = new LinkedList<String []>();
        String ele = new String();
        int count = 0;
        //logger.info("----i We get : " + System.getProperty("user.dir") + "/../" + readSrc);
        try {
            //Obtain the csv file from wherever directory it's located in.
            Scanner csv = new Scanner(new File(System.getProperty("user.dir") + "/../" + readSrc));
            //Acquire the header, as it's formatted differently than subsequent lines.
            ele = csv.nextLine();
            //logger.info("We have : " + ele);
            list.add(ele.split(","));
            while (csv.hasNext()) {
                String [] cells = new String[3]; //Must be generalized ******
                String [] temp; // = null;
                ele = csv.nextLine();
                count ++;
                logger.info("Line " + count + " contains: " + ele);
                if(!vEnd.isEmpty()) {
                    //-1 to ignore the comma separator
                    temp = ele.substring(0, ele.indexOf(vEnd) - 1).split(",");
                    for (int i = 0; i < temp.length; i++) {
                        //Any original commas are restored with the regex shown in replaceAll.\\Q and \\E are to escape any
                        //pattern with special regex meaning that the user enters.
                        cells[i] = temp[i].replaceAll("\\Q" + delim + "\\E", ",");
                    }

                    cells[2] = (ele.substring(ele.indexOf(vEnd) + 1));
                    list.add(cells);
                }
                else{
                    list.add(ele.split(","));
                }

                //list.add(ele.substring(0, ele.indexOf(vEnd)).split(","));
                //WIth the array of stuff stored, go through each index and replace all delim's with a comma.
                //Instead of a direct add, have the ele.substring..... be stored within an array.
                //Then Set up a vector type with ele.substring(ele.indexOf(vEnd) and have both components
                // stored within a mp3Obj then add that to the list!
            }
        }
        catch (Exception e) {
            logger.error("Issue with readCSV:" + e.getMessage());
        }//cat   ch

        return list;
    }

    public void loadRegion (LinkedList<String [] > list, Region putRegion, String objName) throws Exception {
        String [] header;//  = null; //This may not even be needed
        Object prev;

        header = list.pop();
        int i = 0;
        logger.info("We are now using Header:  " + Arrays.toString(header));
        while(!list.isEmpty()) {

            String[] elements; //= new String[header.length];
            /*String[] temp;
            temp = list.pop();
            for (int j = 0; j < temp.length; j++){
                elements[j] = temp[j];
            }*/
            /*for(int j = 0; j < elements.length; j++){
                logger.info("Loading : " + elements[j]);
            }*/

            elements = list.pop();
            logger.info("Putting " + String.valueOf(i) + " With region: " + putRegion.getFullPath() + " Along with name: " + objName +
                    " Of no. elements: " + elements.length + " Along with: " + Arrays.toString(elements));

            if(elements.length != header.length){
                throw new Exception("Wrong number of attributes read from the expected " +
                        "amount was detected. The issue could be from readLine. Try using command" +
                        "sed -ir 's/\\s/ /g' [filename] to get rid of any possible  line-separator" +
                        "characters (unicode 0x2028, then re-load it");
            }
            /*prev =  putRegion.put(String.valueOf(i), new Portfolio(i));
            if(prev != null && prev instanceof Portfolio){
                logger.info("Portfolio Has : " + prev.toString());
            }*/
            /*
            try {                             //All classes will be established within this location
                Class item = Class.forName("org.apache.geode.cache.query.internal." + objName);
                logger.info("USING: " + item.getName() + " WITH: " + this.getClass().toString());
                Class [] types = {this.getClass()};
                logger.info("The types: " + Arrays.toString(types));
                Constructor constructor = item.getConstructor(types);
                logger.info("Constructor name: " + constructor.getName());
                Object[] parameters = {new Double(0), this};
                Object table = constructor.newInstance(elements,i);
                logger.info(table.toString());

                prev = putRegion.put(String.valueOf(i), table);
                if(prev != null) {
                    logger.info("Previous: " + prev.toString());
                }
            } catch (ClassNotFoundException e) {
                logger.info("Class not found: " + e.getMessage());
            } catch (NoSuchMethodException e) {
                logger.info("Method Doesn't Exist: " + e.getMessage());
            } catch (IllegalAccessException e) {
                logger.info("Illegal Access: " + e.getMessage());
            } catch (InstantiationException e) {
                logger.info("Instantiation Error: " + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.info("Invocation Error: " + e.getMessage());
            }*/

            //-----------------------
            prev = putRegion.put(String.valueOf(i), new WebTables(elements, i));
            if(prev != null){
                logger.info("Previous:" + prev.toString());
            }
            //----------------------

            i++;
        }

    }


    //Simple getType method. Used by other things no doubt to check if it's a load query.
    public int getType() {
        return LITERAL_load;
    }

    //Used by DataCommandFunction.java. Need to have a regionPath added to the set that it's checking for!
    public void getRegionsInQuery(Set regionNames, Object[] parameters){
        regionNames.add(this.region.getRegionPath());
    }
}
