package life.sunhao.pool;

import life.sunhao.pojo.MajiangCard;
import life.sunhao.service.MajiangService;

import java.util.List;

/**
 * @Name: MajiangPool
 * @Author: sunhao
 * @Date 2021-12-26 14:12
 */
public class MajiangPool {

    /**
     * 整副麻将牌
     */
    public static List<MajiangCard> initCardPool;

    /**
     * 剩下的麻将牌
     */
    public static List<MajiangCard> leftCardPool;

    /**
     * 手中的麻将牌
     */
    public static List<MajiangCard> handCardPool;

    static {
        // 初始化整副麻将牌
        initCardPool = MajiangService.createMajiangAll();
        // 剩下的麻将牌还是完整的一副
        leftCardPool = initCardPool;
    }
}
