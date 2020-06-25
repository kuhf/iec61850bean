/** This class file was automatically generated by ASN1bean (http://www.beanit.com) */
package com.beanit.iec61850bean.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerNull;
import com.beanit.asn1bean.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class GetNameListRequest implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private ObjectClass objectClass = null;
  private ObjectScope objectScope = null;
  private Identifier continueAfter = null;

  public GetNameListRequest() {}

  public GetNameListRequest(byte[] code) {
    this.code = code;
  }

  public ObjectClass getObjectClass() {
    return objectClass;
  }

  public void setObjectClass(ObjectClass objectClass) {
    this.objectClass = objectClass;
  }

  public ObjectScope getObjectScope() {
    return objectScope;
  }

  public void setObjectScope(ObjectScope objectScope) {
    this.objectScope = objectScope;
  }

  public Identifier getContinueAfter() {
    return continueAfter;
  }

  public void setContinueAfter(Identifier continueAfter) {
    this.continueAfter = continueAfter;
  }

  @Override
  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      reverseOS.write(code);
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    int sublength;

    if (continueAfter != null) {
      codeLength += continueAfter.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, PRIMITIVE, 2
      reverseOS.write(0x82);
      codeLength += 1;
    }

    sublength = objectScope.encode(reverseOS);
    codeLength += sublength;
    codeLength += BerLength.encodeLength(reverseOS, sublength);
    // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
    reverseOS.write(0xA1);
    codeLength += 1;

    sublength = objectClass.encode(reverseOS);
    codeLength += sublength;
    codeLength += BerLength.encodeLength(reverseOS, sublength);
    // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
    reverseOS.write(0xA0);
    codeLength += 1;

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  @Override
  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int tlByteCount = 0;
    int vByteCount = 0;
    BerTag berTag = new BerTag();

    if (withTag) {
      tlByteCount += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    tlByteCount += length.decode(is);
    int lengthVal = length.val;
    vByteCount += berTag.decode(is);

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      vByteCount += length.decode(is);
      objectClass = new ObjectClass();
      vByteCount += objectClass.decode(is, null);
      vByteCount += length.readEocIfIndefinite(is);
      vByteCount += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match mandatory sequence component.");
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
      vByteCount += length.decode(is);
      objectScope = new ObjectScope();
      vByteCount += objectScope.decode(is, null);
      vByteCount += length.readEocIfIndefinite(is);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match mandatory sequence component.");
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
      continueAfter = new Identifier();
      vByteCount += continueAfter.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    }

    if (lengthVal < 0) {
      if (!berTag.equals(0, 0, 0)) {
        throw new IOException("Decoded sequence has wrong end of contents octets");
      }
      vByteCount += BerLength.readEocByte(is);
      return tlByteCount + vByteCount;
    }

    throw new IOException(
        "Unexpected end of sequence, length tag: " + lengthVal + ", bytes decoded: " + vByteCount);
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{");
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (objectClass != null) {
      sb.append("objectClass: ");
      objectClass.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("objectClass: <empty-required-field>");
    }

    sb.append(",\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (objectScope != null) {
      sb.append("objectScope: ");
      objectScope.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("objectScope: <empty-required-field>");
    }

    if (continueAfter != null) {
      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("continueAfter: ").append(continueAfter);
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class ObjectScope implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] code = null;
    private BerNull vmdSpecific = null;
    private Identifier domainSpecific = null;
    private BerNull aaSpecific = null;

    public ObjectScope() {}

    public ObjectScope(byte[] code) {
      this.code = code;
    }

    public BerNull getVmdSpecific() {
      return vmdSpecific;
    }

    public void setVmdSpecific(BerNull vmdSpecific) {
      this.vmdSpecific = vmdSpecific;
    }

    public Identifier getDomainSpecific() {
      return domainSpecific;
    }

    public void setDomainSpecific(Identifier domainSpecific) {
      this.domainSpecific = domainSpecific;
    }

    public BerNull getAaSpecific() {
      return aaSpecific;
    }

    public void setAaSpecific(BerNull aaSpecific) {
      this.aaSpecific = aaSpecific;
    }

    @Override
    public int encode(OutputStream reverseOS) throws IOException {

      if (code != null) {
        reverseOS.write(code);
        return code.length;
      }

      int codeLength = 0;
      if (aaSpecific != null) {
        codeLength += aaSpecific.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 2
        reverseOS.write(0x82);
        codeLength += 1;
        return codeLength;
      }

      if (domainSpecific != null) {
        codeLength += domainSpecific.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 1
        reverseOS.write(0x81);
        codeLength += 1;
        return codeLength;
      }

      if (vmdSpecific != null) {
        codeLength += vmdSpecific.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 0
        reverseOS.write(0x80);
        codeLength += 1;
        return codeLength;
      }

      throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
    }

    @Override
    public int decode(InputStream is) throws IOException {
      return decode(is, null);
    }

    public int decode(InputStream is, BerTag berTag) throws IOException {

      int tlvByteCount = 0;
      boolean tagWasPassed = (berTag != null);

      if (berTag == null) {
        berTag = new BerTag();
        tlvByteCount += berTag.decode(is);
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
        vmdSpecific = new BerNull();
        tlvByteCount += vmdSpecific.decode(is, false);
        return tlvByteCount;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
        domainSpecific = new Identifier();
        tlvByteCount += domainSpecific.decode(is, false);
        return tlvByteCount;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
        aaSpecific = new BerNull();
        tlvByteCount += aaSpecific.decode(is, false);
        return tlvByteCount;
      }

      if (tagWasPassed) {
        return 0;
      }

      throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
      ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
      encode(reverseOS);
      code = reverseOS.getArray();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      appendAsString(sb, 0);
      return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

      if (vmdSpecific != null) {
        sb.append("vmdSpecific: ").append(vmdSpecific);
        return;
      }

      if (domainSpecific != null) {
        sb.append("domainSpecific: ").append(domainSpecific);
        return;
      }

      if (aaSpecific != null) {
        sb.append("aaSpecific: ").append(aaSpecific);
        return;
      }

      sb.append("<none>");
    }
  }
}