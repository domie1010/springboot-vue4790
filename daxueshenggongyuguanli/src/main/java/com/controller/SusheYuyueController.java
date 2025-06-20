
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 入住申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/susheYuyue")
public class SusheYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(SusheYuyueController.class);

    private static final String TABLE_NAME = "susheYuyue";

    @Autowired
    private SusheYuyueService susheYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//宿舍公告
    @Autowired
    private NewsService newsService;//校园资讯信息
    @Autowired
    private ShangpinService shangpinService;//二手商品
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//二手商品收藏
    @Autowired
    private ShangpinCommentbackService shangpinCommentbackService;//二手商品评价
    @Autowired
    private ShangpinOrderService shangpinOrderService;//二手商品订单
    @Autowired
    private SuguanService suguanService;//宿管
    @Autowired
    private SusheService susheService;//宿舍
    @Autowired
    private SusheTousuService susheTousuService;//宿舍投诉
    @Autowired
    private SusheTuisuService susheTuisuService;//退宿申请
    @Autowired
    private SushexueshengService sushexueshengService;//宿舍学生
    @Autowired
    private XueshengService xueshengService;//学生
    @Autowired
    private XueshengKaoqinService xueshengKaoqinService;//学生考勤
    @Autowired
    private XueshengKaoqinListService xueshengKaoqinListService;//学生考勤详情
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("宿管".equals(role))
            params.put("suguanId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = susheYuyueService.queryPage(params);

        //字典表数据转换
        List<SusheYuyueView> list =(List<SusheYuyueView>)page.getList();
        for(SusheYuyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheYuyueEntity susheYuyue = susheYuyueService.selectById(id);
        if(susheYuyue !=null){
            //entity转view
            SusheYuyueView view = new SusheYuyueView();
            BeanUtils.copyProperties( susheYuyue , view );//把实体数据重构到view中
            //级联表 宿舍
            //级联表
            SusheEntity sushe = susheService.selectById(susheYuyue.getSusheId());
            if(sushe != null){
            BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setSusheId(sushe.getId());
            }
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(susheYuyue.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheYuyueEntity susheYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheYuyue:{}",this.getClass().getName(),susheYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            susheYuyue.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SusheYuyueEntity> queryWrapper = new EntityWrapper<SusheYuyueEntity>()
            .eq("sushe_id", susheYuyue.getSusheId())
            .eq("xuesheng_id", susheYuyue.getXueshengId())
            .in("sushe_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheYuyueEntity susheYuyueEntity = susheYuyueService.selectOne(queryWrapper);
        if(susheYuyueEntity==null){
            susheYuyue.setInsertTime(new Date());
            susheYuyue.setSusheYuyueYesnoTypes(1);
            susheYuyue.setCreateTime(new Date());
            susheYuyueService.insert(susheYuyue);
            return R.ok();
        }else {
            if(susheYuyueEntity.getSusheYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(susheYuyueEntity.getSusheYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheYuyueEntity susheYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,susheYuyue:{}",this.getClass().getName(),susheYuyue.toString());
        SusheYuyueEntity oldSusheYuyueEntity = susheYuyueService.selectById(susheYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            susheYuyue.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            susheYuyueService.updateById(susheYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody SusheYuyueEntity susheYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,susheYuyueEntity:{}",this.getClass().getName(),susheYuyueEntity.toString());

        SusheYuyueEntity oldSusheYuyue = susheYuyueService.selectById(susheYuyueEntity.getId());//查询原先数据

        if(susheYuyueEntity.getSusheYuyueYesnoTypes() == 2){//通过

            SusheEntity susheEntity = susheService.selectById(oldSusheYuyue.getSusheId());
            if(susheEntity == null)
                return R.error("查不到宿舍");
            XueshengEntity xueshengEntity = xueshengService.selectById(oldSusheYuyue.getXueshengId());
            if(xueshengEntity == null)
                return R.error("查不到学生");


            List<SushexueshengEntity> yizhuList = sushexueshengService.selectList(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", susheEntity.getId())
            );
            if(yizhuList.size()>=6){
                return R.error("该宿舍已经入住6人,不能同意此入住");
            }


            SushexueshengEntity sushexueshengEntity1 = sushexueshengService.selectOne(new EntityWrapper<SushexueshengEntity>()
                    .eq("xuesheng_id", xueshengEntity.getId())
            );
            if(sushexueshengEntity1 != null)
                return R.error("该学生已经在宿舍中,不能添加为当前宿舍成员");

            SushexueshengEntity sushexueshengEntity = sushexueshengService.selectOne(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", susheEntity.getId())
                    .eq("xuesheng_id", xueshengEntity.getId())
            );
            if(sushexueshengEntity != null)
                return R.error("该学生已经在该宿舍中");





            SushexueshengEntity sushexuesheng = new SushexueshengEntity();
            sushexuesheng.setSusheId(susheEntity.getId());
            sushexuesheng.setXueshengId(xueshengEntity.getId());
            sushexuesheng.setCreateTime(new Date());
            sushexuesheng.setInsertTime(new Date());
            sushexueshengService.insert(sushexuesheng);

        }else if(susheYuyueEntity.getSusheYuyueYesnoTypes() == 3){//拒绝
        }
        susheYuyueEntity.setSusheYuyueShenheTime(new Date());//审核时间
        susheYuyueService.updateById(susheYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SusheYuyueEntity> oldSusheYuyueList =susheYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        susheYuyueService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer xueshengId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<SusheYuyueEntity> susheYuyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SusheYuyueEntity susheYuyueEntity = new SusheYuyueEntity();
//                            susheYuyueEntity.setSusheYuyueUuidNumber(data.get(0));                    //申请编号 要改的
//                            susheYuyueEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            susheYuyueEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            susheYuyueEntity.setSusheYuyueText(data.get(0));                    //申请理由 要改的
//                            susheYuyueEntity.setInsertTime(date);//时间
//                            susheYuyueEntity.setSusheYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            susheYuyueEntity.setSusheYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            susheYuyueEntity.setSusheYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            susheYuyueEntity.setCreateTime(date);//时间
                            susheYuyueList.add(susheYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //申请编号
                                if(seachFields.containsKey("susheYuyueUuidNumber")){
                                    List<String> susheYuyueUuidNumber = seachFields.get("susheYuyueUuidNumber");
                                    susheYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> susheYuyueUuidNumber = new ArrayList<>();
                                    susheYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("susheYuyueUuidNumber",susheYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //申请编号
                        List<SusheYuyueEntity> susheYuyueEntities_susheYuyueUuidNumber = susheYuyueService.selectList(new EntityWrapper<SusheYuyueEntity>().in("sushe_yuyue_uuid_number", seachFields.get("susheYuyueUuidNumber")));
                        if(susheYuyueEntities_susheYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SusheYuyueEntity s:susheYuyueEntities_susheYuyueUuidNumber){
                                repeatFields.add(s.getSusheYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [申请编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        susheYuyueService.insertBatch(susheYuyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = susheYuyueService.queryPage(params);

        //字典表数据转换
        List<SusheYuyueView> list =(List<SusheYuyueView>)page.getList();
        for(SusheYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheYuyueEntity susheYuyue = susheYuyueService.selectById(id);
            if(susheYuyue !=null){


                //entity转view
                SusheYuyueView view = new SusheYuyueView();
                BeanUtils.copyProperties( susheYuyue , view );//把实体数据重构到view中

                //级联表
                    SusheEntity sushe = susheService.selectById(susheYuyue.getSusheId());
                if(sushe != null){
                    BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSusheId(sushe.getId());
                }
                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(susheYuyue.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SusheYuyueEntity susheYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,susheYuyue:{}",this.getClass().getName(),susheYuyue.toString());
        Wrapper<SushexueshengEntity> queryWrapper = new EntityWrapper<SushexueshengEntity>()
            .eq("xuesheng_id", susheYuyue.getXueshengId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SushexueshengEntity sushexueshengEntity = sushexueshengService.selectOne(queryWrapper);
        if(sushexueshengEntity==null){


            List<SushexueshengEntity> yizhuList = sushexueshengService.selectList(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", susheYuyue.getSusheId())
            );
            if(yizhuList.size()>=6){
                return R.error("该宿舍已经入住6人,不能同意此入住");
            }


            susheYuyue.setInsertTime(new Date());
            susheYuyue.setSusheYuyueYesnoTypes(1);
            susheYuyue.setCreateTime(new Date());
            susheYuyueService.insert(susheYuyue);
            return R.ok();
        }else {
            SusheEntity susheEntity = susheService.selectById(sushexueshengEntity.getSusheId());
            return R.error(511,"该学生已经在"+susheEntity.getSusheName()+"中,不能申请成为其他宿舍成员");
        }
    }

}

