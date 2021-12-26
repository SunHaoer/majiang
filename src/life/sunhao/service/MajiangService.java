package life.sunhao.service;

import life.sunhao.constants.MajiangConstants;
import life.sunhao.enums.MajiangTypeEnum;
import life.sunhao.pojo.MajiangCard;
import life.sunhao.pool.MajiangPool;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Name: MajiangService
 * @Author: sunhao
 * @Date 2021-12-26 14:04
 */
public class MajiangService {

    /**
     * 创建整副麻将牌
     */
    public static void createMajiangAll() {
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
        // 赋值
        MajiangPool.leftCardList = allMajiangCardList;
    }

    /**
     * 摸一张牌
     */
    public static MajiangCard touchOne() {
        Random random = new Random();
        Integer cardIndex = random.nextInt(MajiangPool.leftCardList.size());
        // 得到一张手牌
        MajiangCard card = MajiangPool.leftCardList.get(cardIndex);
        // 从剩余牌组中移除
        MajiangPool.leftCardList.remove(card);
        // 放入手牌中
        MajiangPool.handCardList.add(card);
        return card;
    }

    /**
     * 模拟开局的第一此摸牌
     */
    public static void initTouch() {
        for (int i = 0; i < MajiangConstants.HAND_CARD_NUM; i++) {
            // 摸牌
            touchOne();
        }
    }

    /**
     * 整理手牌 按照 风，条，饼，万 的顺序排序
     */
    public static void arrangeHandCard() {
        MajiangPool.handCardList = arrangeCardList(MajiangPool.handCardList);
    }

    /**
     * 整理麻将牌
     * @param majiangCardList
     * @return
     */
    public static List<MajiangCard> arrangeCardList(List<MajiangCard> majiangCardList) {
        majiangCardList = majiangCardList.stream().sorted(Comparator.comparing(MajiangCard::getCode)).collect(Collectors.toList());
        return majiangCardList;
    }

}
