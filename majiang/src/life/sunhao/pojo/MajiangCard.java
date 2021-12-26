package life.sunhao.pojo;

import life.sunhao.constants.MajiangConstants;
import life.sunhao.enums.MajiangTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: MajiangItem 单张麻将牌
 * @Author: sunhao
 * @Date 2021-12-26 12:52
 */
public class MajiangCard {

    /**
     * 类型： 风，条，饼，万
     */
    public MajiangTypeEnum type;

    /**
     * 数值
     */
    public Integer value;

    /**
     * 每个麻将对应的code
     */
    public Integer code;

    /**
     * 中文名
     */
    public String nameCn;

    public MajiangCard(MajiangTypeEnum type, Integer value) {
        this.type = type;
        this.value = value;
        this.code = getCode(type, value);
        this.nameCn = getNameCn(type, value);
    }

    /**
     * 计算麻将牌对应的 code
     * @param type
     * @param value
     * @return
     */
    private Integer getCode(MajiangTypeEnum type, Integer value) {
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
    private String getNameCn(MajiangTypeEnum type, Integer value) {
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

}
