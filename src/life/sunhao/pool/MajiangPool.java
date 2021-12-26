package life.sunhao.pool;

import life.sunhao.pojo.MajiangCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: MajiangPool
 * @Author: sunhao
 * @Date 2021-12-26 14:12
 */
public class MajiangPool {

    /**
     * 剩下的麻将牌
     */
    public static List<MajiangCard> leftCardList;

    /**
     * 手中的麻将牌
     */
    public static List<MajiangCard> handCardList;

    static {
        leftCardList = new ArrayList<>();
        handCardList = new ArrayList<>();
    }

}
