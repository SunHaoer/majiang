package life.sunhao.service;

import life.sunhao.constants.MajiangConstants;
import life.sunhao.enums.MajiangTypeEnum;
import life.sunhao.pojo.MajiangCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: MajiangService
 * @Author: sunhao
 * @Date 2021-12-26 14:04
 */
public class MajiangService {

    /**
     * 创建整副麻将牌
     */
    public static List<MajiangCard> createMajiangAll() {
        List<MajiangCard> allMajiangCardList = new ArrayList<>();
        for (MajiangTypeEnum type : MajiangTypeEnum.values()) {
            // 风，条，饼，万 类型
            for (int value = 1; value <= type.getTypeNum(); value++) {
                // 牌面数值
                for (int i = 0; i < MajiangConstants.ONE_TYPE_MAX; i++) {
                    // 每种类型的麻将牌创建 4 个
                    allMajiangCardList.add(new MajiangCard(type, value));
                }
            }
        }
        return allMajiangCardList;
    }



}
