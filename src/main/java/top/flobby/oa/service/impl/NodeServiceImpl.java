package top.flobby.oa.service.impl;

import top.flobby.oa.entity.Node;
import top.flobby.oa.mapper.NodeMapper;
import top.flobby.oa.service.NodeService;
import top.flobby.oa.utils.MybatisUtils;

import java.util.List;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-28 15:31
 **/

public class NodeServiceImpl implements NodeService {

    @Override
    public List<Node> getNodeList(Long userId) {
        return (List<Node>) MybatisUtils.executeQuery(sqlSession -> {
            NodeMapper mapper = sqlSession.getMapper(NodeMapper.class);
            return mapper.getNode(userId);
        });
    }
}
