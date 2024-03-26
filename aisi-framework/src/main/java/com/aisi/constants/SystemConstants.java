package com.aisi.constants;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 10:32
 * @Description:
 */


public class SystemConstants {
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 分类是正常状态
     */
    public static final String Category_STATUS_NORMAL = "0";
    /**
     * 分类是禁用状态
     */
    public static final String Category_STATUS_DISABLE = "1";

    /**
     * 友联是已审核状态
     */
    public static final String LINk_STATUS_NORMAL = "0";

//    /**
//     * 评论是正常状态
//     */
//    public static final int COMMENT_STATUS_NORMAL = 0;
//    /**
//     * 评论是禁用状态
//     */
//    public static final int COMMENT_STATUS_DISABLE = 0;

    public static final Long Comment_ROOT_ID = -1L;

    public static String Link_Comment_Type = "1";
    public static String Article_Comment_Type = "0";
}
