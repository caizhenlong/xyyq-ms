import {ProForm, ProFormDigit, ProFormInstance, ProFormText} from '@ant-design/pro-components';
import {useRef, useState} from 'react';
import {ProFormRadio} from "@ant-design/pro-form/es";
import {MaterialInfoReport, uploadFile} from "@/services/ant-design-pro/api";
import {message} from "antd";
import UploadButton from '@ant-design/pro-form/es/components/UploadButton';

export default () => {
  const formData = new FormData();

  const formRef = useRef<ProFormInstance<{
    name: string;
    company?: string;
    useMode?: string;
  }>>();
  const [componentDisabled, setComponentDisabled] = useState<boolean>(false);
  // @ts-ignore
  // @ts-ignore
  return (
    <ProForm<{
      name: string;
      company?: string;
      useMode?: string;
    }>
      disabled={componentDisabled}
      onFinish={async (values: API.MaterialInfoParams) => {
        try {
          // @ts-ignore
          values.img = formData.get("img");
          const result = await MaterialInfoReport(values);
          if (result.code == 0) {
            const defaultLoginSuccessMessage = '物资添加成功！';
            message.success(defaultLoginSuccessMessage);
            setComponentDisabled(true)
            return;
          } else {
            const defaultLoginFailureMessage = result.description;
            message.error(defaultLoginFailureMessage);
          }
        } catch (error: any) {
          const defaultLoginFailureMessage = '物资添加失败，请重试！';
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
          name="typeName"
          label="物资类型"
          placeholder="请输入物资类型"
          rules={[
            {
              required: true,
              message: '物资类型是必填项!',
            },
          ]}
        />
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
        <ProFormText
          width="sm"
          name="specification"
          label="物资规格"
          placeholder="请输入物资规格"
          rules={[
            {
              required: true,
              message: '物资规格是必填项!',
            },
          ]}
        />
        <ProFormText
          width="sm"
          name="unit"
          label="物资单位"
          placeholder="请输入物资单位"
          rules={[
            {
              required: true,
              message: '物资单位是必填项!',
            },
          ]}
        />
      </ProForm.Group>
      <ProForm.Group>
        <ProFormText
          width="sm"
          name="creator"
          label="创建人"
          placeholder="请输入创建人"
          rules={[
            {
              required: true,
              message: '创建人是必填项!',
            },
          ]}
        />
        <ProFormDigit label="库存" name="total" width="sm" min={1} max={1000}
                      rules={[
                        {
                          required: true,
                          message: '库存是必填项!',
                        },
                      ]}

        />
      </ProForm.Group>
      <ProForm.Group>
        <UploadButton
          name="img"
          label="物资图片"
          max={5}
          fieldProps={{
            name: 'file',
            listType: 'picture-card',
          }}
          rules={[
            {
              required: true,
              message: '物资图片是必填项!',
            },
          ]}
          action={async (file) => {
            try {
              formData.append('file', file)
              const result = await uploadFile(formData);
              if (result.code == 0) {
                const defaultLoginSuccessMessage = '图片上传成功！';
                message.success(defaultLoginSuccessMessage);
                console.log(result.data)
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
          }}
        />
      </ProForm.Group>
      <ProForm.Group>
        < ProFormRadio.Group
          name="status"
          label="是否启用"
          options={
            [
              {
                label: '禁用',
                value: '0',
              },
              {
                label: '启用',
                value: '1',
              }
              ,
            ]
          }
          rules={
            [
              {
                required: true,
                message: '是否启用是必填项!',
              },
            ]
          }
        />

      </ProForm.Group>
      <ProForm.Group>
        <ProFormText
          width="md"
          name="description"
          label="备注"
          placeholder="请输入备注"
        />
      </ProForm.Group>
    </ProForm>
  )
    ;
};
