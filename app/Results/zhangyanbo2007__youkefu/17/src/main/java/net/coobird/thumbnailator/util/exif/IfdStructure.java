package net.coobird.thumbnailator.util.exif;
 public class IfdStructure {

 private  int tag;

 private  IfdType type;

 private  int count;

 private  int offsetValue;

/**
 * Instantiates a IFD with the given attributes.
 *
 * @param tag			The tag element.
 * @param type			The type element.
 * @param count			The count of values.
 * @param offsetValue	The offset or value.
 */
public IfdStructure(int tag, int type, int count, int offsetValue) {
    super();
    this.tag = tag;
    this.type = IfdType.typeOf(type);
    this.count = count;
    this.offsetValue = offsetValue;
}
public boolean isValue(){
    /*
		 * The offsetValue field contains a value if the size of the value is
		 * less than or equal to 4 bytes see "Value Offset" in Section 4.6.3
		 * of the Exif version 2.3 specification.
		 */
    return type.size() * count <= 4;
}


public IfdType getType(){
    return type;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + count;
    result = prime * result + offsetValue;
    result = prime * result + tag;
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
}


public int getOffsetValue(){
    return offsetValue;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    IfdStructure other = (IfdStructure) obj;
    if (count != other.count)
        return false;
    if (offsetValue != other.offsetValue)
        return false;
    if (tag != other.tag)
        return false;
    if (type != other.type)
        return false;
    return true;
}


public boolean isOffset(){
    return !isValue();
}


@Override
public String toString(){
    return "IfdStructure [tag=" + Integer.toHexString(tag) + ", type=" + type + ", count=" + count + ", offsetValue=" + offsetValue + "]";
}


public int getTag(){
    return tag;
}


public int getCount(){
    return count;
}


}