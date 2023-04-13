import {ProForm, ProFormText,} from '@ant-design/pro-components';
import {Button, message, Upload} from 'antd';
import React, {useState} from 'react';
import styles from './index.less';
import {ProFormSelect} from "@ant-design/pro-form/es";
import {currentUser as queryCurrentUser, updateUser, uploadFile} from "@/services/ant-design-pro/api";
import {UploadOutlined} from "@ant-design/icons";

const UserInfo: React.FC = () => {
  const [data, setData] = useState<API.CurrentUser>();
  const [userId, setUserId] = useState<string>();
  const [avatarUrl, setAvatarUrl] = useState<string>();


  const formData = new FormData();

// 头像组件 方便以后独立，增加裁剪之类的功能
  const AvatarView = ({avatar}: { avatar: string }) => (
    <>
      <div className={styles.avatar_title}>头像</div>
      <div className={styles.avatar}>
        <img src={avatar} alt="avatar"/>
      </div>
      <Upload showUploadList={false} action={async (file) => {
        try {
          formData.append('file', file)
          const result = await uploadFile(formData);
          if (result.code == 0) {
            const defaultLoginSuccessMessage = '图片上传成功！';
            message.success(defaultLoginSuccessMessage);
            setAvatarUrl(result.data)
            formData.append("img", result.data);
            return;
          } else {
            const defaultLoginFailureMessage = result.description;
            message.error(defaultLoginFailureMessage);
          }
        } catch (error: any) {
          const defaultLoginFailureMessage = '图片上传失败，请重试！';
          message.error(defaultLoginFailureMessage);
        }
      }}>
        <div className={styles.button_view}>
          <Button>
            <UploadOutlined/>
            更换头像
          </Button>
        </div>
      </Upload>
    </>
  );


  const getAvatarURL = () => {
    if (avatarUrl) {
      return avatarUrl;
    }
    return '';
  };

  const handleFinish = async (data) => {
    try {
      const result = await updateUser({
        avatarUrl: avatarUrl,
        id: userId,
        ...data
      });
      if (result.code == 0) {
        message.success('更新基本信息成功');
        //history.push("/notice/noticeInfo");
        window.location.reload() // 强制页面刷新
      } else {
        const defaultLoginFailureMessage = result.description;
        message.error(defaultLoginFailureMessage);
      }
    } catch (error: any) {
      const defaultLoginFailureMessage = '修改失败，请重试！';
      message.error(defaultLoginFailureMessage);
    }
  };

  return (
    <>
      <h1>基本资料</h1>
      <div className={styles.baseView}>
        <>
          <div className={styles.left}>
            <ProForm
              layout="vertical"
              onFinish={handleFinish}
              submitter={{
                searchConfig: {
                  submitText: '更新基本信息',
                },
                render: (_, dom) => dom[1],
              }}
              hideRequiredMark
              request={async () => {
                try {
                  const res = await queryCurrentUser();
                  setData(res.data);
                  setUserId(res.data.id)
                  setAvatarUrl(res.data.avatarUrl)
                  return res.data;
                } catch (error) {
                  const defaultLoginFailureMessage = '获取基本资料失败';
                  message.error(defaultLoginFailureMessage);
                }
                return undefined;
              }}

            >
              <ProFormText
                width="md"
                name="username"
                label="姓名"
                rules={[
                  {
                    required: true,
                    message: '请输入您的姓名!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="phone"
                label="联系电话"
                rules={[
                  {
                    required: true,
                    message: '请输入您的联系电话!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="email"
                label="邮箱"
                rules={[
                  {
                    required: true,
                    message: '请输入个人邮箱!',
                  },
                ]}
              />
              <ProFormSelect
                width="sm"
                name="gender"
                label="性别"
                rules={[
                  {
                    required: true,
                    message: '请选择您的性别!',
                  },
                ]}
                options={[
                  {
                    label: '男',
                    value: '1'
                  },
                  {
                    label: '女',
                    value: '2'
                  },
                ]}
              />

              <ProFormText
                width="md"
                name="department"
                label="院系"
                rules={[
                  {
                    required: true,
                    message: '请输入院系!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="classes"
                label="班级"
                rules={[
                  {
                    required: true,
                    message: '请输入班级!',
                  },
                ]}
              />
              <ProFormText
                width="md"
                name="dormitoryNo"
                label="宿舍号"
                rules={[
                  {
                    required: true,
                    message: '请输入班级!',
                  },
                ]}
              />
            </ProForm>
          </div>
          <div className={styles.right}>
            <AvatarView avatar={getAvatarURL()}/>
          </div>
        </>
      </div>
    </>
  );
};
export default UserInfo;
