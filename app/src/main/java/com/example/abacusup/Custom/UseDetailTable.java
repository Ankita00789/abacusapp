package com.example.abacusup.Custom;

import android.provider.BaseColumns;

public class UseDetailTable {

    private UseDetailTable() {
    }

    public static class UseDetailTable1 implements BaseColumns {
        public static final String TABLE_NAME = "Facdetails";
        public static final String COLUMNNAME_ID = "id";
        public static final String COLUMNNAME_NAME = "name";
        public static final String COLUMNNAME_Mobile = "mobile_no";
        public static final String COLUMNNAME_Email = "email_id";
        public static final String COLUMNNAME_University_name = "University_name";
        public static final String COLUMNNAME_College_name = "College_name";
    }
}
