<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div>
<table width="901"  border="1" style="background:url(../image/login_1.jpg)" align="center"  bordercolor="white">
      <tr>
        <td height="102"  align="right" valign="bottom"><Font Style="font-weight:BOLD;size:6;color:#FFE4C4;">${applicationScope.existUser.username}，你好&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</Font><a href="/managerwebb/main.jsp"><Font Style="font-weight:BOLD;color:#8B008B">首页</Font></a>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="/managerwebb/invalidate.jsp"><Font Style="font-weight:BOLD;color:#8B008B">退出</Font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
</div>