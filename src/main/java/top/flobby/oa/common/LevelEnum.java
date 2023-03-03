package top.flobby.oa.common;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description : 等级枚举
 * @create : 2023-03-03 13:34
 **/

public enum LevelEnum {
    /**
     * 总经理
     */
    BOSS(8, "总经理"),
    /**
     * 部门经理
     */
    DEPARTMENT_MANAGER(7, "部门经理");

    /**
     * 级别
     */
    final Integer level;
    /**
     * 描述
     */
    final String des;

    LevelEnum(Integer level, String des) {
        this.level = level;
        this.des = des;
    }

    public Integer getLevel() {
        return level;
    }
}
