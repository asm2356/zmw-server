package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author AZhao
 */
@TableName(value = "statistics")
public class Statistics implements Serializable {
    private static final long serialVersionUID = -5234836819650132689L;
    @TableId(value = "id")
    private String id;
    @TableField(value = "s_count")
    private int count;
    @TableField(value = "s_date")
    private Date date;
    @TableField(value = "s_week")
    private int week;
    @TableField(value = "s_type")
    private int type;


    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, date, week, type);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", date=" + date +
                ", week=" + week +
                ", type=" + type +
                '}';
    }
}
