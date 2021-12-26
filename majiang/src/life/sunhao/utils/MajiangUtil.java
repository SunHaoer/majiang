package life.sunhao.utils;

import life.sunhao.pojo.MajiangCard;
import life.sunhao.service.MajiangService;

import java.util.List;

/**
 * @Name: MajiangUtil
 * @Author: sunhao
 * @Date 2021-12-26 15:17
 */
public class MajiangUtil {

    /**
     * 打印麻将牌
     * @param cardList
     */
    public static void print(List<MajiangCard> cardList) {
        // 整理麻将牌
        cardList = MajiangService.arrangeCardList(cardList);
        // 打印
        System.out.print(cardList.get(0).getNameCn() + " ");
        for (int i = 1; i < cardList.size(); i++) {
            // 上一张麻将牌
            MajiangCard lastCard = cardList.get(i - 1);
            // 当前麻将牌
            MajiangCard nowCard = cardList.get(i);
            if (!nowCard.getType().equals(lastCard.getType())) {
                // 类型不同了， 换行
                System.out.println();
            }
            // 打印当前麻将牌
            System.out.print(nowCard.getNameCn() + " ");
        }
        System.out.println();
    }

}
