package life.sunhao.pojo;

import life.sunhao.constants.MajiangConstants;
import life.sunhao.enums.MajiangTypeEnum;

import java.util.Arrays;

/**
 * @Name: MajiangItem 单张麻将牌
 * @Author: sunhao
 * @Date 2021-12-26 12:52
 */
public class MajiangCard {

    /**
     * 类型： 风，条，饼，万
     */
    private MajiangTypeEnum type;

    /**
     * 数值
     */
    private Integer value;

    /**
     * 每个麻将对应的code
     */
    private Integer code;

    /**
     * 中文名
     */
    private String nameCn;

    public MajiangCard(MajiangTypeEnum type, Integer value) {
        this.type = type;
        this.value = value;
        this.code = calculateCode(type, value);
        this.nameCn = calculateNameCn(type, value);
    }

    /**
     * 根据中文名创建麻将牌
     * @param nameCn
     */
    public MajiangCard(String nameCn) {
        this.nameCn = nameCn;
        // 获取麻将牌类型
        String typeStr = nameCn.charAt(1) + "";
        MajiangTypeEnum type = null;
        if (MajiangTypeEnum.nameCnMap.containsKey(typeStr)) {
            type = MajiangTypeEnum.nameCnMap.get(typeStr);
        } else {
            type = MajiangTypeEnum.FENG;
        }
        this.type = type;
        // 处理牌面数值
        String valueStr = nameCn.charAt(0) + "";
        Integer value = -1;
        // 处理风板
        if (MajiangTypeEnum.FENG.equals(type)) {
            value = Arrays.asList(MajiangConstants.FENG_ARR).indexOf(nameCn);
        } else {
            value = Integer.valueOf(valueStr);
        }
        this.value = value;
        // code
        this.code = calculateCode(type, value);
    }

    /**
     * 计算麻将牌对应的 code
     * @param type
     * @param value
     * @return
     */
    private Integer calculateCode(MajiangTypeEnum type, Integer value) {
        Integer code = -1;
        if (MajiangTypeEnum.FENG.equals(type)) {
            // 风板
            code = value * MajiangTypeEnum.FENG.getBaseCode();
        } else if (MajiangTypeEnum.TIAO.equals(type)) {
            // 条
            code = MajiangTypeEnum.TIAO.getBaseCode() + value;
        } else if (MajiangTypeEnum.BING.equals(type)) {
            // 饼
            code = MajiangTypeEnum.BING.getBaseCode() + value;
        } else if (MajiangTypeEnum.WAN.equals(type)) {
            // 万
            code = MajiangTypeEnum.WAN.getBaseCode() + value;
        } else {
            throw new RuntimeException("该麻将牌不存在!");
        }
        return code;
    }

    /**
     * 获取中文名
     * @return
     */
    private String calculateNameCn(MajiangTypeEnum type, Integer value) {
        String nameCn = "";
        if (MajiangTypeEnum.FENG.equals(type)) {
            // 风板特殊处理
            if (value < 0 || value > MajiangTypeEnum.FENG.getTypeNum()) {
                throw new RuntimeException("该麻将牌不存在!");
            }
            nameCn = MajiangConstants.FENG_ARR[value];
        } else {
            // 条，饼，万
            nameCn = value + type.getNameCn();
        }
        return nameCn;
    }



    public MajiangTypeEnum getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

    public String getNameCn() {
        return nameCn;
    }

}
