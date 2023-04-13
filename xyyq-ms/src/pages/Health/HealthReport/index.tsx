import type {ProFormInstance} from '@ant-design/pro-components';
import {ProForm, ProFormText,} from '@ant-design/pro-components';
import {useRef, useState} from 'react';
import {ProFormRadio} from "@ant-design/pro-form/es";
import {healthReport} from "@/services/ant-design-pro/api";
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
      onFinish={async (values: API.HealthSmParams) => {
        try {
          const result = await healthReport(values);
          if (result.code == 0) {
            const defaultLoginSuccessMessage = '健康上报成功！';
            message.success(defaultLoginSuccessMessage);
            setComponentDisabled(true)
            return;
          } else {
            const defaultLoginFailureMessage = result.description;
            message.error(defaultLoginFailureMessage);
          }
        } catch (error: any) {
          const defaultLoginFailureMessage = '健康上报失败，请重试！';
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
      < ProForm.Group>
        < ProFormRadio.Group
          name="physicalCondition"
          label="您今天的身体状况如何"
          options={
            [
              {
                label: '正常',
                value: '1',
              },
              {
                label: '有发热、咳嗽、呼吸困难等症状',
                value: '2',
              }
              ,
              {
                label: '其他症状',
                value: '3',
              }
              ,
            ]
          }
          rules={
            [
              {
                required: true,
                message: '身体状况是必填项!',
              },
            ]
          }
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormText
          width="md"
          name="location"
          label="现在所在地"
          placeholder="请输入现在所在地"
          rules={[
            {
              required: true,
              message: '现在所在地是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormRadio.Group
          name="isHighRisk"
          label="是否在高风险地区"
          options={[

            {
              label: '否',
              value: '0',
            },
            {
              label: '是',
              value: '1',
            },
          ]}
          rules={[
            {
              required: true,
              message: '是否高风险地区是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormRadio.Group
          name="healthCodeStatus"
          label="您的健康码状态"
          options={[

            {
              label: '绿码',
              value: '1',
            },
            {
              label: '黄码',
              value: '2',
            },
            {
              label: '红码',
              value: '3',
            },
          ]}
          rules={[
            {
              required: true,
              message: '健康码状态是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormRadio.Group
          name="atSchool"
          label="目前是否在校"
          options={[

            {
              label: '是',
              value: '1',
            },
            {
              label: '否',
              value: '0',
            },
          ]}
          rules={[
            {
              required: true,
              message: '是否在校是必填项!',
            },
          ]}
        />
      </ProForm.Group>
    </ProForm>
  )
    ;
};
