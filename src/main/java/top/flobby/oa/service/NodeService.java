package top.flobby.oa.service;

import top.flobby.oa.entity.Node;

import java.util.List;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-02-28 15:31
 **/

public interface NodeService {
    /**
     * 得到节点列表
     *
     * @param userId 用户id
     * @return {@link List}<{@link Node}>
     */
    List<Node> getNodeList(Long userId);
}
