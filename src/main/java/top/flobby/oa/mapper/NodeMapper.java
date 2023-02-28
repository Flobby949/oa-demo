package top.flobby.oa.mapper;

import top.flobby.oa.entity.Node;

import java.util.List;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-28 15:24
 **/

public interface NodeMapper {

    /**
     * 得到节点
     *
     * @param userId 用户id
     * @return {@link List}<{@link Node}>
     */
    List<Node> getNode(Long userId);
}
