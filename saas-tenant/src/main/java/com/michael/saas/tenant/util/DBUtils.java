package com.michael.saas.tenant.util;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class DBUtils {

    private static final String PACK = "com.michael.saas.tenant.domain";

    public static void createDataBase(String url, String name, String user, String pwd){
        Connection conn = null;
        Statement stat = null;
        try {
            url += "?user=" + user + "&password=" + pwd + "&characterEncoding=UTF8";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();

            String sql = "drop database if exists " + name;
            stat.execute(sql);

            sql = "create database " + name + " CHARACTER SET UTF8";
            stat.execute(sql);

            sql = "use".concat(" ").concat(name);
            stat.execute(sql);

            DBUtils.createTables(PACK, stat);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != stat){
                    stat.close();
                }
                if (null != conn){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTables(String pack,Statement stat) throws Exception{

        Set<Class<?>> domians = DBUtils.getClasss(pack);

        for (Class<?> domian : domians) {

            DBUtils.createTable(domian, stat);

        }

    }

    public static void createTable(Class<?> clazz, Statement stat) throws Exception{
        String tableName = "";
        Annotation [] annos = clazz.getAnnotations();
        boolean isEntity = false;
        for (Annotation anno : annos){
            if ("Entity".equals(anno.annotationType().getSimpleName())){
                isEntity = true;
            }else if ("Table".equals(anno.annotationType().getSimpleName())){
                Table table = clazz.getAnnotation(Table.class);
                if (StringUtils.isNotBlank(table.name())){
                    tableName = table.name();
                }else {
                    tableName = clazz.getSimpleName();
                }
            }
        }

        if (isEntity){

            StringBuffer dropSql = new StringBuffer("DROP TABLE IF EXISTS ");
            dropSql.append("`".concat(tableName).concat("`"));
            System.out.println(dropSql.toString());
            stat.execute(dropSql.toString());

            StringBuffer tableStr = new StringBuffer("CREATE TABLE");
            tableStr.append(" `".concat(tableName).concat("` ("));
            tableStr.append(DBUtils.getColumns(clazz));
            tableStr.append(")".concat(" ").concat("ENGINE=InnoDB DEFAULT CHARSET=utf8"));
            System.out.println(tableStr.toString());
            stat.execute(tableStr.toString());

        }

    }

    private static String getColumns(Class<?> clazz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                columns.append("`");
                if (StringUtils.isNotBlank(column.name())){
                    columns.append(column.name());
                }else {
                    columns.append(field.getName());
                }
                columns.append("`");
                columns.append(" ".concat(getSqlType(field, column)));
                columns.append(",");
            }
        }
        columns.append("PRIMARY KEY (`id`)");
        return columns.toString();
    }

    public static String getSqlType(Field field, Column column){
        StringBuffer columnType = new StringBuffer();

        Class<?> type = field.getType();

        switch (type.getSimpleName()){
            case "String" :
                columnType.append("varchar");
                if (column.length() != 0){
                    columnType.append("(".concat(String.valueOf(column.length())).concat(")"));
                }
                break;
            case "BigDecimal" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "Date" :
                if(field.isAnnotationPresent(Temporal.class)){
                    Temporal temporal = field.getAnnotation(Temporal.class);
                    if (TemporalType.TIMESTAMP.equals(temporal.value())){
                        columnType.append("datetime");
                    }else {
                        columnType.append("date");
                    }
                }else {
                    columnType.append("datetime");
                }
                break;
            case "Integer" :
                columnType.append("int");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(")"));
                }else {
                    columnType.append("(".concat(String.valueOf(11)).concat(")"));
                }
                break;
            case "Double" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "double" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "int" :
                columnType.append("int");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(")"));
                }else {
                    columnType.append("(".concat(String.valueOf(11)).concat(")"));
                }
                break;
        }

        if(StringUtils.isNotBlank(column.columnDefinition())){
            columnType.append(" ".concat("DEFAULT").concat(column.columnDefinition()));
        }else {
            columnType.append(" ".concat("DEFAULT NULL"));
        }

        return columnType.toString();
    }

    private static Set<Class<?>> getClasss(String pack){
        return ClassTools.getClasses(pack);
    }


    public static void main(String[] args) {
        DBUtils.createDataBase("jdbc:mysql://127.0.0.1:3306","saas_tenant1","root", "root");
        //DBUtils.createTables(PACK, null);
    }





}
