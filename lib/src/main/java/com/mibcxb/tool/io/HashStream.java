package com.mibcxb.tool.io;

public interface HashStream {
    public enum Algorithm {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256");

       public final String algoName;

        Algorithm(String algoName) {
            this.algoName = algoName;
        }
    }

     byte[] getHash();
}
