package com.flexink.common.code;

public class FxBootType {
	public static final String Y = "Y";
    public static final String N = "N";

    public enum Used {
        Y, N
    }

    public enum Deleted {
        Y, N;
        
        public static Deleted get(String delYn) {
            for (Deleted deleted : Deleted.values()) {
                if (deleted.name().equals(delYn))
                    return deleted;
            }
            return null;
        }
    }
    

    public enum DataStatus {
        CREATED,
        MODIFIED,
        DELETED,
        ORIGIN
    }
}
