import {ProForm, ProFormDigit, ProFormInstance, ProFormText} from '@ant-design/pro-components';
import {useRef, useState} from 'react';
import {materialOut} from "@/services/ant-design-pro/api";
import {message} from "antd";

export default () => {
  const formRef = useRef<ProFormInstance<{
    name: string;
    company?: string;
    useMode?: string;
  }>>();
  const [componentDisabled] = useState<boolean>(false);
  // @ts-ignore
  return (
    <ProForm<{
      name: string;
      company?: string;
      useMode?: string;
    }>
      disabled={componentDisabled}
      onFinish={async (values: API.MaterialOutParams) => {
        try {
          const result = await materialOut(values);
          if (result.code == 0) {
            const defaultLoginSuccessMessage = '物资出库成功！';
            message.success(defaultLoginSuccessMessage);
            /*setComponentDisabled(true)*/
            return;
          } else {
            const defaultLoginFailureMessage = result.description;
            message.error(defaultLoginFailureMessage);
          }
        } catch (error: any) {
          const defaultLoginFailureMessage = '物资出库失败，请重试！';
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
        <ProFormText
          width="sm"
          name="name"
          label="物资名称"
          placeholder="请输入物资名称"
          rules={[
            {
              required: true,
              message: '物资名称是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormDigit label="出库数量" name="quantity" width="sm" min={1} max={1000}
                      rules={[
                        {
                          required: true,
                          message: '出库数量是必填项!',
                        },
                      ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormText
          width="sm"
          name="superintendent"
          label="出库负责人"
          placeholder="请输入出库负责人"
          rules={[
            {
              required: true,
              message: '出库负责人是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormText
          width="sm"
          name="reason"
          label="出库事由"
          placeholder="请输入出库事由"
          rules={[
            {
              required: true,
              message: '出库事由是必填项!',
            },
          ]}
        />
      </ProForm.Group>
    </ProForm>
  )
    ;
};
