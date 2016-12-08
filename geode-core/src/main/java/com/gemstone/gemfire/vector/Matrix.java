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

package com.gemstone.gemfire.vector;

/**
 * Created by tenpercent on 12/1/16.
 */
public class Matrix {

    private double[] data;
    private int height;
    private int width;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.data = new double[height * width];
    }

    /**
     * Multiplies matrix by vector from the right
     *
     * @param v
     * @return A v
     */
    public Vector multVectorR(final Vector v) {
        assert v.size == this.width;

        Vector prod = new Vector(this.height);
        for (int i_prod = 0; i_prod < this.height; ++i_prod) {
            for (int i_v = 0; i_v < this.width; ++i_v) {
                prod.setData(i_prod,
                        prod.getData(i_prod)
                                + v.getData(i_v) * this.getData(i_prod, i_v));
            }
        }
        return prod;
    }
    /**
     * Multiplies matrix by matrix
     *
     * @param B
     * @return AB
     */
    public Matrix multiply(Matrix B){
        Matrix A = this;
        if (A.height != B.width) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.width, B.height);
        for (int i = 0; i < C.width; i++)
            for (int j = 0; j < C.height; j++)
                for (int k = 0; k < A.height; k++)
                    C.setData(i, j, (A.getData(i, k) * B.getData(k, j)));
        return C;
    }

    /**
     * Adds matrix plus matrix
     *
     * @param B
     * @return A + B
     */
    public Matrix plus(Matrix B){
        Matrix A = this;
        if (B.width != A.width || B.height != A.height) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                C.setData(i, j, A.getData(i,j) + B.getData(i,j));
        return C;
    }

    /**
     * Substract matrix minus matrix
     *
     * @param B
     * @return A - B
     */
    public Matrix minus(Matrix B){
        Matrix A = this;
        if (B.width != A.width || B.height != A.height) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                C.setData(i, j, A.getData(i,j) - B.getData(i,j));
        return C;
    }

    /**
     *
     * does A = B ?
     *
     * @param B
     * @return boolean
     */
    public boolean equals(Matrix B) {
        Matrix A = this;
        if (B.width != A.width || B.height != A.height) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++){
                int c = Double.compare(A.getData(i, j), B.getData(i, j));
                if (c != 0)
                    return false;
            }

        return true;
    }

    /**
     * Multiplies matrix by vector from the left
     *
     * @param v
     * @return (v^T) A
     */
    public Vector multVectorL(final Vector v) {
        assert v.size == this.height;

        Vector prod = new Vector(this.width);

        for (int i_v = 0; i_v < this.height; ++i_v) {
            for (int i_prod = 0; i_prod < this.width; ++i_prod) {
                prod.setData(i_prod,
                        prod.getData(i_prod)
                                + v.getData(i_v) * this.getData(i_v, i_prod));
            }
        }
        return prod;
    }


    public void setRow(int iRow, final Vector v) {
        assert v.size == this.width;

        for (int i_col = 0; i_col < this.width; ++i_col) {
            setData(iRow, i_col, v.getData(i_col));
        }
    }

    public void setCol(int iCol, final Vector v) {
        assert v.size == this.height;

        for (int i_row = 0; i_row < this.height; ++i_row) {
            setData(i_row, iCol, v.getData(i_row));
        }
    }

    public double getData(int iRow, int iCol) {
        return data[iRow * width + iCol];
    }

    public void setData(int iRow, int iCol, double value) {
        data[iRow * width + iCol] = value;
    }

}
