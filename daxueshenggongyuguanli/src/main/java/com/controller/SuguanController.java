
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
 * 宿管
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/suguan")
public class SuguanController {
    private static final Logger logger = LoggerFactory.getLogger(SuguanController.class);

    private static final String TABLE_NAME = "suguan";

    @Autowired
    private SuguanService suguanService;


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
    private SusheService susheService;//宿舍
    @Autowired
    private SusheTousuService susheTousuService;//宿舍投诉
    @Autowired
    private SusheTuisuService susheTuisuService;//退宿申请
    @Autowired
    private SusheYuyueService susheYuyueService;//入住申请
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
        PageUtils page = suguanService.queryPage(params);

        //字典表数据转换
        List<SuguanView> list =(List<SuguanView>)page.getList();
        for(SuguanView c:list){
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
        SuguanEntity suguan = suguanService.selectById(id);
        if(suguan !=null){
            //entity转view
            SuguanView view = new SuguanView();
            BeanUtils.copyProperties( suguan , view );//把实体数据重构到view中
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
    public R save(@RequestBody SuguanEntity suguan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,suguan:{}",this.getClass().getName(),suguan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SuguanEntity> queryWrapper = new EntityWrapper<SuguanEntity>()
            .eq("username", suguan.getUsername())
            .or()
            .eq("suguan_phone", suguan.getSuguanPhone())
            .or()
            .eq("suguan_id_number", suguan.getSuguanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuguanEntity suguanEntity = suguanService.selectOne(queryWrapper);
        if(suguanEntity==null){
            suguan.setCreateTime(new Date());
            suguan.setPassword("123456");
            suguanService.insert(suguan);
            return R.ok();
        }else {
            return R.error(511,"账户或者宿管手机号或者宿管身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SuguanEntity suguan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,suguan:{}",this.getClass().getName(),suguan.toString());
        SuguanEntity oldSuguanEntity = suguanService.selectById(suguan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(suguan.getSuguanPhoto()) || "null".equals(suguan.getSuguanPhoto())){
                suguan.setSuguanPhoto(null);
        }

            suguanService.updateById(suguan);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SuguanEntity> oldSuguanList =suguanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        suguanService.deleteBatchIds(Arrays.asList(ids));

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
            List<SuguanEntity> suguanList = new ArrayList<>();//上传的东西
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
                            SuguanEntity suguanEntity = new SuguanEntity();
//                            suguanEntity.setUsername(data.get(0));                    //账户 要改的
//                            //suguanEntity.setPassword("123456");//密码
//                            suguanEntity.setSuguanName(data.get(0));                    //宿管姓名 要改的
//                            suguanEntity.setSuguanPhone(data.get(0));                    //宿管手机号 要改的
//                            suguanEntity.setSuguanIdNumber(data.get(0));                    //宿管身份证号 要改的
//                            suguanEntity.setSuguanPhoto("");//详情和图片
//                            suguanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            suguanEntity.setSuguanEmail(data.get(0));                    //宿管邮箱 要改的
//                            suguanEntity.setCreateTime(date);//时间
                            suguanList.add(suguanEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //宿管手机号
                                if(seachFields.containsKey("suguanPhone")){
                                    List<String> suguanPhone = seachFields.get("suguanPhone");
                                    suguanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> suguanPhone = new ArrayList<>();
                                    suguanPhone.add(data.get(0));//要改的
                                    seachFields.put("suguanPhone",suguanPhone);
                                }
                                //宿管身份证号
                                if(seachFields.containsKey("suguanIdNumber")){
                                    List<String> suguanIdNumber = seachFields.get("suguanIdNumber");
                                    suguanIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> suguanIdNumber = new ArrayList<>();
                                    suguanIdNumber.add(data.get(0));//要改的
                                    seachFields.put("suguanIdNumber",suguanIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<SuguanEntity> suguanEntities_username = suguanService.selectList(new EntityWrapper<SuguanEntity>().in("username", seachFields.get("username")));
                        if(suguanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SuguanEntity s:suguanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //宿管手机号
                        List<SuguanEntity> suguanEntities_suguanPhone = suguanService.selectList(new EntityWrapper<SuguanEntity>().in("suguan_phone", seachFields.get("suguanPhone")));
                        if(suguanEntities_suguanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SuguanEntity s:suguanEntities_suguanPhone){
                                repeatFields.add(s.getSuguanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [宿管手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //宿管身份证号
                        List<SuguanEntity> suguanEntities_suguanIdNumber = suguanService.selectList(new EntityWrapper<SuguanEntity>().in("suguan_id_number", seachFields.get("suguanIdNumber")));
                        if(suguanEntities_suguanIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SuguanEntity s:suguanEntities_suguanIdNumber){
                                repeatFields.add(s.getSuguanIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [宿管身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        suguanService.insertBatch(suguanList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        SuguanEntity suguan = suguanService.selectOne(new EntityWrapper<SuguanEntity>().eq("username", username));
        if(suguan==null || !suguan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(suguan.getId(),username, "suguan", "宿管");
        R r = R.ok();
        r.put("token", token);
        r.put("role","宿管");
        r.put("username",suguan.getSuguanName());
        r.put("tableName","suguan");
        r.put("userId",suguan.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody SuguanEntity suguan, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<SuguanEntity> queryWrapper = new EntityWrapper<SuguanEntity>()
            .eq("username", suguan.getUsername())
            .or()
            .eq("suguan_phone", suguan.getSuguanPhone())
            .or()
            .eq("suguan_id_number", suguan.getSuguanIdNumber())
            ;
        SuguanEntity suguanEntity = suguanService.selectOne(queryWrapper);
        if(suguanEntity != null)
            return R.error("账户或者宿管手机号或者宿管身份证号已经被使用");
        suguan.setCreateTime(new Date());
        suguanService.insert(suguan);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        SuguanEntity suguan = suguanService.selectById(id);
        suguan.setPassword("123456");
        suguanService.updateById(suguan);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        SuguanEntity suguan = suguanService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(suguan.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(suguan.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        suguan.setPassword(newPassword);
		suguanService.updateById(suguan);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        SuguanEntity suguan = suguanService.selectOne(new EntityWrapper<SuguanEntity>().eq("username", username));
        if(suguan!=null){
            suguan.setPassword("123456");
            suguanService.updateById(suguan);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrSuguan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        SuguanEntity suguan = suguanService.selectById(id);
        if(suguan !=null){
            //entity转view
            SuguanView view = new SuguanView();
            BeanUtils.copyProperties( suguan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = suguanService.queryPage(params);

        //字典表数据转换
        List<SuguanView> list =(List<SuguanView>)page.getList();
        for(SuguanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SuguanEntity suguan = suguanService.selectById(id);
            if(suguan !=null){


                //entity转view
                SuguanView view = new SuguanView();
                BeanUtils.copyProperties( suguan , view );//把实体数据重构到view中

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
    public R add(@RequestBody SuguanEntity suguan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,suguan:{}",this.getClass().getName(),suguan.toString());
        Wrapper<SuguanEntity> queryWrapper = new EntityWrapper<SuguanEntity>()
            .eq("username", suguan.getUsername())
            .or()
            .eq("suguan_phone", suguan.getSuguanPhone())
            .or()
            .eq("suguan_id_number", suguan.getSuguanIdNumber())
//            .notIn("suguan_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuguanEntity suguanEntity = suguanService.selectOne(queryWrapper);
        if(suguanEntity==null){
            suguan.setCreateTime(new Date());
            suguan.setPassword("123456");
        suguanService.insert(suguan);

            return R.ok();
        }else {
            return R.error(511,"账户或者宿管手机号或者宿管身份证号已经被使用");
        }
    }

}

