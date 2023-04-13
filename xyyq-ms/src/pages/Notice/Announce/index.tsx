import type {ProFormInstance} from '@ant-design/pro-components';
import {ProForm, ProFormTextArea,} from '@ant-design/pro-components';
import {useRef, useState} from 'react';
import {ProFormRadio} from "@ant-design/pro-form/es";
import {announce} from "@/services/ant-design-pro/api";
import {message} from "antd";

export default () => {
  const formRef = useRef<ProFormInstance<{
    name: string;
    company?: string;
    useMode?: string;
  }>>();
  const [componentDisabled, setComponentDisabled] = useState<boolean>(false);
  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  return (
    <ProForm<{
      name: string;
      company?: string;
      useMode?: string;
    }>
      disabled={componentDisabled}
      onFinish={async (values: API.NoticeInfoParams) => {
        try {
          const result = await announce(values);
          if (result.code == 0) {
            const defaultLoginSuccessMessage = '公告发布成功！';
            message.success(defaultLoginSuccessMessage);
            setComponentDisabled(true)
            return;
          } else {
            const defaultLoginFailureMessage = result.description;
            message.error(defaultLoginFailureMessage);
          }
        } catch (error: any) {
          const defaultLoginFailureMessage = '公告发布失败，请重试！';
          message.error(defaultLoginFailureMessage);
        }
      }}
      formRef={formRef}
      params={
        {
          id: '100'
        }
      }
      formKey="base-form-use-demo"
      dateFormatter={(value, valueType) => {
        console.log('---->', value, valueType);
        return value.format('YYYY/MM/DD HH:mm:ss');
      }
      }
      autoFocusFirstInput
    >
      <ProForm.Group>
        <ProFormTextArea
          width="md"
          name="content"
          label="公告内容"
          placeholder="请输入公告内容"
          rules={[
            {
              required: true,
              message: '公告内容是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormRadio.Group
          name="type"
          label="公告类型"
          options={[
            {
              label: '普通通知',
              value: '0',
            },
            {
              label: '讲座通知',
              value: '1',
            },
            {
              label: '重要公告',
              value: '2',
            },
          ]}
          rules={[
            {
              required: true,
              message: '公告类型是必填项!',
            },
          ]}
        />
      </ProForm.Group>
    </ProForm>
  )
    ;
};
