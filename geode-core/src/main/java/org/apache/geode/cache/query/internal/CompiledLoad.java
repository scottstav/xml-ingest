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

    final Logger logger = LogService.getLogger();
    private Vector item = null;
    //private Mp3Obj thing = null;

    public CompiledLoad(CompiledLiteral filePath, CompiledLiteral delim, CompiledLiteral vLim, CompiledRegion region){
        this.filePath = filePath;
        this.region = region;
        this.delim = delim;
        this.vLim = vLim;
        //this.key = key;

    }
        //Set up a collection interface called LoadResults later
    public Object evaluate(ExecutionContext context) throws FunctionDomainException, TypeMismatchException,
            NameResolutionException, QueryInvocationTargetException {
        Object duh = null;
        logger.info("----WITHIN THE EVALUATE OF LOAD!!!!--i --");
        LinkedList <String []> list = new LinkedList<String []> ();

        //This will extract the string within filePath....apparently PdxString is the middleman for that.
        String region = this.filePath.getSavedPdxString().toString();

        try{
            //For scaling up:
            //LinkedList <mp3Obj> list = new LinkedList<mp3Obj>();
            //LinkedList <mp3Obj> taken = new LinkedList<String []>();
            //File mp3 = new File("cello.mp3");
            //If it's current directory, it can just be ./


            list = readCSV(delim.getSavedPdxString().toString(), vLim.getSavedPdxString().toString(), region, context.getCache().getRegion(region));

            while(!list.isEmpty()){
                String [] thing = list.pop();
                for(int i = 0; i < thing.length; i++){
                    logger.info(thing[i]);
                }
            }
        }//end of try
        catch (Exception e) {
            logger.error("Issue occured in evaluate " + e.getMessage());
            e.printStackTrace();
        }


        return duh;
    }


    public LinkedList <String []> readCSV(String delim, String vEnd,  String readSrc, Region putRegion) {
        // Vector stream = new Vector(20);
        String[] mp3Info = new String[8];
        LinkedList<String[]> list = new LinkedList<String[]>();
        //Attach a '/' if dirName is missing one
        String ele = new String();
        logger.info("----i We get : " + System.getProperty("user.dir") + "/../" + readSrc);
        try {
            //Obtain the csv file from wherever directory it's located in.
            Scanner csv = new Scanner(new File(System.getProperty("user.dir") + "/../" + readSrc));
            while (csv.hasNext()) {
                ele = csv.nextLine();
                logger.info("WE HAVE: " + ele + "------");
                //logger.info(ele + " ------ spot: " + vEnd + " -----"  + ele.substring( 0 ,ele.indexOf(vEnd)));
                list.add(ele.split(","));
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





    //Simple getType method. Used by other things no doubt to check if it's a load query.
    public int getType() {
        return LITERAL_load;
    }

    //Used by DataCommandFunction.java. Need to have a regionPath added to the set that it's checking for!
    public void getRegionsInQuery(Set regionNames, Object[] parameters){
        regionNames.add(this.region.getRegionPath());
    }
}
