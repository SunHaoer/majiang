package life.sunhao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: MajiangItemEnum
 * @Author: sunhao
 * @Date 2021-12-26 13:05
 */
public enum MajiangTypeEnum {

    FENG("风", 10, 7),
    TIAO("条", 100, 9),
    BING("饼", 1000, 9),
    WAN("万", 10000, 9);

    private String nameCn;
    private Integer baseCode;
    private Integer typeNum;

    public static Map<String, MajiangTypeEnum> nameCnMap;

    MajiangTypeEnum(String nameCn, Integer baseCode, Integer typeNum) {
        this.nameCn = nameCn;
        this.baseCode = baseCode;
        this.typeNum = typeNum;
    }

    public String getNameCn() {
        return nameCn;
    }

    public Integer getBaseCode() {
        return baseCode;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    static {
        nameCnMap = new HashMap();
        for (MajiangTypeEnum item : MajiangTypeEnum.values()) {
            nameCnMap.put(item.getNameCn(), item);
        }
    }

}
