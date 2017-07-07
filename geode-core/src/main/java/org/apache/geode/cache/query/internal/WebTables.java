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
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

/**
 * Created by dylan on 7/13/17.
 */
public class WebTables implements Serializable, DataSerializable{
    String File_Name = "";
    String column1 = "";
    String column2 = "";
    String column3 = "";
    String column4 = "";
    String column5 = "";
    String column6 = "";
    String column7 = "";
    String column8 = "";
    String column9 = "";
    String column10 = "";
    String column11 = "";
    String column12 = "";
    String column13 = "";
    String column14 = "";
    String column15 = "";
    String column16 = "";
    String column17 = "";
    String column18 = "";
    String column19 = "";
    String column20 = "";
    String column21 = "";
    String column22 = "";
    String column23 = "";
    String column24 = "";
    String column25 = "";
    String column26 = "";
    String column27 = "";
    String column28 = "";
    String column29 = "";
    int Row_Num;
    int ID;

    public WebTables(){

    }

    public WebTables(int ID){

        this.ID = ID;
    }

    public WebTables(String [] metaData, int ID){
        this.ID = ID;
        this.File_Name = metaData[0];
        this.column1 = metaData[1];
        this.column2 = metaData[2];
        this.column3 = metaData[3];
        this.column4 = metaData[4];
        this.column5 = metaData[5];
        this.column6 = metaData[6];
        this.column7 = metaData[7];
        this.column8 = metaData[8];
        this.column9 = metaData[9];
        this.column10 = metaData[10];
        this.column11 = metaData[11];
        this.column12 = metaData[12];
        this.column13 = metaData[13];
        this.column14 = metaData[14];
        this.column15 = metaData[15];
        this.column16 = metaData[16];
        this.column17 = metaData[17];
        this.column18 = metaData[18];
        this.column19 = metaData[19];
        this.column20 = metaData[20];
        this.column21 = metaData[21];
        this.column22 = metaData[22];
        this.column23 = metaData[23];
        this.column24 = metaData[24];
        this.column25 = metaData[25];
        this.column26 = metaData[26];
        this.column27 = metaData[27];
        this.column28 = metaData[28];
        this.column29 = metaData[29];
        this.Row_Num = Integer.parseInt(metaData[30]);

    }

    public int getRow_Num () { return this.Row_Num; }
    public String getFile_Name() { return this.File_Name; }
    public String getColumn1 () { return this.column1;}
    public String getColumn2 () { return this.column2;}
    public String getColumn3 () { return this.column3;}
    public String getColumn4 () { return this.column4;}
    public String getColumn5 () { return this.column5;}
    public String getColumn6 () { return this.column6;}
    public String getColumn7 () { return this.column7;}
    public String getColumn8 () { return this.column8;}
    public String getColumn9 () { return this.column9;}
    public String getColumn10 () { return this.column10;}
    public String getColumn11 () { return this.column11;}
    public String getColumn12 () { return this.column12;}
    public String getColumn13 () { return this.column13;}
    public String getColumn14 () { return this.column14;}
    public String getColumn15 () { return this.column15;}
    public String getColumn16 () { return this.column16;}
    public String getColumn17 () { return this.column17;}
    public String getColumn18 () { return this.column18;}
    public String getColumn19 () { return this.column19;}
    public String getColumn20 () { return this.column20;}
    public String getColumn21 () { return this.column21;}
    public String getColumn22 () { return this.column22;}
    public String getColumn23 () { return this.column23;}
    public String getColumn24 () { return this.column24;}
    public String getColumn25 () { return this.column25;}
    public String getColumn26 () { return this.column26;}
    public String getColumn27 () { return this.column27;}
    public String getColumn28 () { return this.column28;}
    public String getColumn29 () { return this.column29;}

    public String toString(){
        return  this.ID + "," + this.File_Name + "," + this.column1 + "," + this.column2 + "," + this.column3;
    }

    public void fromData(DataInput in) throws IOException, ClassNotFoundException {
        this.ID = in.readInt();
        this.File_Name = DataSerializer.readString(in);
        this.column1 =  DataSerializer.readString(in);
        this.column2 =DataSerializer.readString(in);
        this.column3 =DataSerializer.readString(in);
        this.column4 =DataSerializer.readString(in);
        this.column5 =DataSerializer.readString(in);
        this.column6 =DataSerializer.readString(in);
        this.column7 =DataSerializer.readString(in);
        this.column8 =DataSerializer.readString(in);
        this.column9 =DataSerializer.readString(in);
        this.column10 =DataSerializer.readString(in);
        this.column11 =DataSerializer.readString(in);
        this.column12 =DataSerializer.readString(in);
        this.column13 =DataSerializer.readString(in);
        this.column14 =DataSerializer.readString(in);
        this.column15 =DataSerializer.readString(in);
        this.column16 =DataSerializer.readString(in);
        this.column17 =DataSerializer.readString(in);
        this.column18 =DataSerializer.readString(in);
        this.column19 =DataSerializer.readString(in);
        this.column20 =DataSerializer.readString(in);
        this.column21 =DataSerializer.readString(in);
        this.column22 =DataSerializer.readString(in);
        this.column23 =DataSerializer.readString(in);
        this.column24 =DataSerializer.readString(in);
        this.column25 =DataSerializer.readString(in);
        this.column26 =DataSerializer.readString(in);
        this.column27 =DataSerializer.readString(in);
        this.column28 =DataSerializer.readString(in);
        this.column29 =DataSerializer.readString(in);
        this.Row_Num =DataSerializer.readInteger(in);

    }

    public void toData(DataOutput out) throws IOException {
        out.writeInt(this.ID);
        DataSerializer.writeString(this.File_Name,out);
        DataSerializer.writeString(this.column1, out);
        DataSerializer.writeString(this.column2, out);
        DataSerializer.writeString(this.column3, out);
        DataSerializer.writeString(this.column4, out);
        DataSerializer.writeString(this.column5, out);
        DataSerializer.writeString(this.column6, out);
        DataSerializer.writeString(this.column7, out);
        DataSerializer.writeString(this.column8, out);
        DataSerializer.writeString(this.column9, out);
        DataSerializer.writeString(this.column10, out);
        DataSerializer.writeString(this.column11, out);
        DataSerializer.writeString(this.column12, out);
        DataSerializer.writeString(this.column13, out);
        DataSerializer.writeString(this.column14, out);
        DataSerializer.writeString(this.column15, out);
        DataSerializer.writeString(this.column16, out);
        DataSerializer.writeString(this.column17, out);
        DataSerializer.writeString(this.column18, out);
        DataSerializer.writeString(this.column19, out);
        DataSerializer.writeString(this.column20, out);
        DataSerializer.writeString(this.column21, out);
        DataSerializer.writeString(this.column22, out);
        DataSerializer.writeString(this.column23, out);
        DataSerializer.writeString(this.column24, out);
        DataSerializer.writeString(this.column25, out);
        DataSerializer.writeString(this.column26, out);
        DataSerializer.writeString(this.column27, out);
        DataSerializer.writeString(this.column28, out);
        DataSerializer.writeString(this.column29, out);
        DataSerializer.writeInteger(this.Row_Num, out);

    }
}
