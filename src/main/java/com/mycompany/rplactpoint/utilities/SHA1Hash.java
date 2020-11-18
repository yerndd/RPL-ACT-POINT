/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.utilities;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author deokr
 */
public class SHA1Hash {
    private String hasil;
    private String awal;

    public SHA1Hash() {
    }

    public SHA1Hash(String awal) {
        this.hasil = new String(Hex.encodeHex(DigestUtils.sha1(awal)));
        this.awal = awal;
    }

    public String getHasil() {
        return hasil;
    }

    public String getAwal() {
        return awal;
    }
}
