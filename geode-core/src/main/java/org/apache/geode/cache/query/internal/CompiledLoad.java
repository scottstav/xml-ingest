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
////
/**
 * Created by dylan on 6/25/17.
 */
public class CompiledLoad extends AbstractCompiledValue{
    private CompiledID filePath;
    private CompiledRegion region;
    final Logger logger = LogService.getLogger();
    private Vector item = null;

    public CompiledLoad(CompiledID filePath, CompiledRegion region){
        this.filePath = filePath;
        this.region = region;


    }
        //Set up a collection interface called LoadResults later
    public Object evaluate(ExecutionContext context) throws FunctionDomainException, TypeMismatchException,
            NameResolutionException, QueryInvocationTargetException {
        Object duh = null;
        logger.info("----WITHIN THE EVALUATE OF LOAD!!!!--i --");

        // Vector stream = new Vector(20);
        String [] mp3Info = new String[8];
        LinkedList <String []> list = new LinkedList<String []> ();
        //Attach a '/' if dirName is missing one
        String ele = new String() ;
        logger.info("----WITHIN THE EVALUATE OF LOAD!!!!--i --");
        try{
            Scanner csv = new Scanner(new File(this.filePath.getId() + "/mp3.csv"));
            while(csv.hasNext()){
                ele = csv.nextLine();
                logger.info("WE HAVE: " + ele);
                //System.out.println(ele + " ------ spot: " + vEnd + " -----"  + ele.substring( 0 ,ele.indexOf(vEnd)));
                //list.add(ele.split(","));
                //list.add(ele.substring(0, ele.indexOf(vEnd)).split(","));
                //WIth the array of stuff stored, go through each index and replace all delim's with a comma.
                //Instead of a direct add, have the ele.substring..... be stored within an array.
                //Then Set up a vector type with ele.substring(ele.indexOf(vEnd) and have both components
                // stored within a mp3Obj then add that to the list!
            }
        }
        catch(Exception e){
            System.err.println("Error1: " + e.getMessage());
            e.printStackTrace();
        }//cat   ch






        return duh;
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
