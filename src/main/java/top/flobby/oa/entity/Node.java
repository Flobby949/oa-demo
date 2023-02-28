package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-28 15:24
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    private Long nodeId;
    private Integer nodeType;
    private String nodeName;
    private String url;
    private Integer nodeCode;
    private Long parentId;
    private String icon;
}
