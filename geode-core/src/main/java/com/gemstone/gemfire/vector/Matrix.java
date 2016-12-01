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
