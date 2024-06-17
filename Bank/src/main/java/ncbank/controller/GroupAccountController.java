package ncbank.controller;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ncbank.beans.AccountBean;
import ncbank.beans.AutoBean;
import ncbank.beans.GroupAccountBean;
import ncbank.beans.UserBean;
import ncbank.mapper.GroupAccountMapper;
import ncbank.service.GroupAccountService;

@Controller
@RequestMapping("/groupAccount")
public class GroupAccountController {

    @Autowired
    private GroupAccountService groupAccountService;

    @Autowired
    private GroupAccountMapper groupAccountMapper;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/create")
    public String create(Model model) {
        if (loginUserBean.isUserLogin()) {
            List<AccountBean> accountList = groupAccountService.getAccountsByUserNum(loginUserBean.getUser_num());
            model.addAttribute("accountList", accountList);
        } else {
            model.addAttribute("accountList", null);
        }
        return "groupAccount/groupAccountCreate";
    }

    @PostMapping("/groupAccountOpened")
    public String opened(@RequestParam String groupname,
                         @RequestParam String grouptype,
                         @RequestParam String accounts,
                         @RequestParam String ac_name,
                         Model model) {
        model.addAttribute("groupname", groupname);
        model.addAttribute("grouptype", grouptype);
        model.addAttribute("accounts", accounts);
        model.addAttribute("ac_name", ac_name);

        String accountNumber = accounts.replaceAll("\\[.*\\]", "").trim();
        List<AutoBean> accountInfoList = groupAccountService.infoList(accountNumber);
        if (!accountInfoList.isEmpty()) {
            model.addAttribute("accountInfo", accountInfoList.get(0));
        }

        return "groupAccount/groupAccountOpened";
    }

    @PostMapping("/groupAccountOpenedNext")
    public String validatePassword(@RequestParam String accounts,
                                   @RequestParam String inputPassword,
                                   @RequestParam String groupname,
                                   @RequestParam String grouptype,
                                   @RequestParam String ac_name,
                                   @RequestParam String auto_next_date,
                                   @RequestParam String auto_type,
                                   @RequestParam String auto_balance,
                                   Model model) {
        String accountNumber = accounts.replaceAll("\\[.*\\]", "").trim();
        AccountBean accountBean = groupAccountService.getAccountByAccountNumber(accountNumber);

        if (accountBean != null && accountBean.getAc_password().equals(inputPassword)) {
            model.addAttribute("groupname", groupname);
            model.addAttribute("grouptype", grouptype);
            model.addAttribute("accounts", accounts);
            model.addAttribute("ac_name", ac_name);
            model.addAttribute("auto_next_date", auto_next_date);
            model.addAttribute("auto_type", auto_type);
            model.addAttribute("auto_balance", auto_balance);

            List<AutoBean> accountInfoList = groupAccountService.infoList(accountNumber);
            if (!accountInfoList.isEmpty()) {
                model.addAttribute("accountInfo", accountInfoList.get(0));
            }

            model.addAttribute("applicationDate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
            return "groupAccount/groupAccountOpenedNext";
        } else {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("groupname", groupname);
            model.addAttribute("grouptype", grouptype);
            model.addAttribute("accounts", accounts);
            model.addAttribute("ac_name", ac_name);
            model.addAttribute("auto_next_date", auto_next_date);
            model.addAttribute("auto_type", auto_type);
            model.addAttribute("auto_balance", auto_balance);

            return "groupAccount/groupAccountOpened";
        }
    }

    @PostMapping("/complete")
    public String complete(@RequestParam String accounts,
                           @RequestParam String groupname,
                           @RequestParam String grouptype,
                           @RequestParam String ac_name,
                           @RequestParam String inputPassword,
                           @RequestParam String auto_next_date,
                           @RequestParam String auto_type,
                           @RequestParam String auto_balance,
                           Model model) {
        String accountNumber = accounts.replaceAll("\\[.*\\]", "").trim();
        AccountBean accountBean = groupAccountService.getAccountByAccountNumber(accountNumber);

        if (accountBean != null && accountBean.getAc_password().equals(inputPassword)) {
            model.addAttribute("groupname", groupname);
            model.addAttribute("grouptype", grouptype);
            model.addAttribute("accounts", accounts);
            model.addAttribute("ac_name", ac_name);

            List<AutoBean> accountInfoList = groupAccountService.infoList(accountNumber);
            if (!accountInfoList.isEmpty()) {
                model.addAttribute("accountInfo", accountInfoList.get(0));
            }

            model.addAttribute("applicationDate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));

            // 그룹 정보 저장
            GroupAccountBean groupAccount = new GroupAccountBean();
            groupAccount.setUser_num(loginUserBean.getUser_num());
            groupAccount.setGroup_joindate(new java.sql.Date(System.currentTimeMillis()));
            groupAccount.setGroup_leader(String.valueOf(loginUserBean.getUser_num()));

            try {
                groupAccountService.createGroupAccount(groupAccount);
            } catch (SQLException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "그룹 정보 저장 중 오류가 발생했습니다.");
                return "groupAccount/groupAccountOpenedNext";
            }
            
            Integer group_num = groupAccountService.getLatestGroupNumByUser(loginUserBean.getUser_num());
            if (group_num != null) {
                GroupAccountBean groupInfo = groupAccountService.getGroupInfo(group_num);
                model.addAttribute("groupInfo", groupInfo);
            } else {
                model.addAttribute("errorMessage", "그룹 번호를 가져오는 중 오류가 발생했습니다.");
                return "groupAccount/groupAccountOpenedNext";
            }

            return "groupAccount/groupAccountComplete";
        } else {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("groupname", groupname);
            model.addAttribute("grouptype", grouptype);
            model.addAttribute("accounts", accounts);
            model.addAttribute("ac_name", ac_name);
            model.addAttribute("auto_next_date", auto_next_date);
            model.addAttribute("auto_type", auto_type);
            model.addAttribute("auto_balance", auto_balance);
            return "groupAccount/groupAccountOpenedNext";
        }
    }

    @PostMapping("/getGroupInfo")
    @ResponseBody
    public GroupAccountBean getGroupInfo(@RequestParam("group_num") int group_num) {
        return groupAccountService.getGroupInfo(group_num);
    }

    @GetMapping("/acceptInvite")
    public String showAcceptInvitePage(@RequestParam("group_num") int group_num, Model model) {
        model.addAttribute("group_num", group_num);
        System.out.println("group_num: " + group_num); // 디버깅 로그 추가
        return "groupAccount/groupAccountAcceptInvite";
    }

    @PostMapping("/acceptInvite")
    public String acceptInvite(@RequestParam int group_num,
                               @RequestParam String action,
                               Model model,
                               HttpSession session) {
        if (!loginUserBean.isUserLogin()) {
            session.setAttribute("redirectAfterLogin", "groupAccount/acceptInviteAction?group_num=" + group_num + "&action=" + action);
            return "redirect:/user/login";
        }

        return handleAcceptInvite(group_num, action, model);
    }

    @GetMapping("/acceptInviteAction")
    public String handleAcceptInvite(@RequestParam int group_num,
                                     @RequestParam String action,
                                     Model model) {
        if (action.equals("accept")) {
            GroupAccountBean groupAccount = new GroupAccountBean();
            groupAccount.setGroup_num(group_num);
            groupAccount.setUser_num(loginUserBean.getUser_num());
            groupAccount.setGroup_joindate(new java.sql.Date(System.currentTimeMillis()));
            groupAccount.setGroup_leader(groupAccountMapper.selectGroupLeaderByGroupNum(group_num));

            try {
                groupAccountService.createGroupAccount(groupAccount);
            } catch (SQLException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "초대 수락 중 오류가 발생했습니다.");
                return "groupAccount/groupAccountAcceptInvite";
            }

            model.addAttribute("message", "초대를 수락하였습니다.");
            return "groupAccount/acceptInviteSuccess";
        } else {
            model.addAttribute("message", "초대를 거절하였습니다.");
            return "groupAccount/groupAccountAcceptInvite";
        }
    }
}



