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


package org.apache.geode.cache.query.internal;

import java.util.Map;
import org.apache.geode.vector.Vector;
//    import entagged.audioformats.*;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

/**
 * Created by dylan on 6/27/17.
 */
public class Mp3Obj implements Serializable, DataSerializable{
    String album = "";
    String artist = "";
    String comment = "";
    String genre = "";
    String title = "";
    String track = "";
    String year = "";
    Vector data = null;
    int ID;

    public Mp3Obj() {

    }

    public Mp3Obj(int ID){
        this.ID = ID;
    }

    public Mp3Obj(String [] metaData, int ID){
        //data =  new Vector(v.size);
        this.album = metaData[0];
        this.artist = metaData[1];
        this.comment = metaData[2];
        this.genre = metaData[3];
        this.title = metaData[4];
        this.track = metaData[5];
        this.year = metaData[6];
        this.ID = ID;
        String [] list = metaData[7].split(",");
        this.data = new Vector(list.length);
        for(int i = 0; i < list.length; i++){
            this.data.setData(i, Double.parseDouble(list[i]));
        }
    }
    public void setAlbum(String str){
        this.album=str;
    }
    public void setArtist(String str){
        this.artist =str;
    }
    public void setComment(String str){
        this.comment=str;
    }
    public void setGenre(String str){
        this.genre=str;
    }
    public void setTitle(String str){
        this.title=str;
    }
    public void setTrack(String str){
        this.track=str;
    }
    public void setYear(String str){
        this.year=str;
    }
    public void setData(Vector vr){
        this.data=vr;
    }

    public String getAlbum(){
        return this.album;
    }
    public String getArtist() {
        return this.artist;
    }
    public String getComment(){
        return this.comment;
    }
    public String getGenre() {
        return this.genre;
    }
    public String getTitle(){
        return this.title;
    }
    public String getTrack() {
        return this.track;
    }
    public String getYear(){
        return this.year;
    }
    public Vector getData(){
        return this.data;
    }


    @Override
    public void toData(DataOutput out) throws IOException {
        out.writeInt(this.ID);
        DataSerializer.writeString(this.album, out);
        DataSerializer.writeString(this.artist, out);
        DataSerializer.writeString(this.comment, out);
        DataSerializer.writeString(this.genre, out);
        DataSerializer.writeString(this.title, out);
        DataSerializer.writeString(this.track, out);
        DataSerializer.writeString(this.year, out);
        //DataSerializer.writeInteger(this.ID, out);
        DataSerializer.writeObject(this.data, out);
    }
    public String toString(){
        return  this.album + "," + this.artist + "," + this.comment + "," + this.genre + "," + this.title + "," + this.track +
                "," + this.year + "," + this.ID + "," + this.data.toString();

    }

    @Override
    public void fromData(DataInput in) throws IOException, ClassNotFoundException {
        this.ID = in.readInt();
        this.album = DataSerializer.readString(in);
        this.artist = DataSerializer.readString(in);
        this.comment = DataSerializer.readString(in);
        this.genre = DataSerializer.readString(in);
        this.title = DataSerializer.readString(in);
        this.track = DataSerializer.readString(in);
        this.year  = DataSerializer.readString(in);

        this.data = DataSerializer.readObject(in);





    }

}
