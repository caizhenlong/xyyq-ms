import {ProCard, ProFormDateTimeRangePicker, ProFormRadio, ProFormText, StepsForm,} from '@ant-design/pro-components';
import {Button, message} from 'antd';
import {addLeaveApply, uploadFile} from "@/services/ant-design-pro/api";
import {ProFormUploadButton} from "@ant-design/pro-form";

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

const formData = new FormData();

export default () => {
  return (
    <ProCard>
      <StepsForm<{
        name: string;
      }>
        onFinish={async (values: API.LeaveApplyParams) => {
          // @ts-ignore
          values.time = values.time.toString()
          try {
            // @ts-ignore
            values.img = formData.get("img");
            // console.log(values);
            const result = await addLeaveApply(values);
            if (result.code == 0) {
              const defaultLoginSuccessMessage = '请假申请成功，待审核';
              message.success(defaultLoginSuccessMessage);
              history.back();
              return;
            } else {
              const defaultLoginFailureMessage = result.description;
              message.error(defaultLoginFailureMessage);
            }
          } catch (error: any) {
            const defaultLoginFailureMessage = '请假申请失败，请重试！';
            message.error(defaultLoginFailureMessage);
          }
        }}
        formProps={{
          validateMessages: {
            required: '此项为必填项',
          },
        }}
        submitter={{
          render: (props) => {
            if (props.step === 0) {
              return (
                <Button type="primary" onClick={() => props.onSubmit?.()}>
                  下一步 {'>'}
                </Button>
              );
            }

            if (props.step === 1) {
              return [
                <Button key="pre" onClick={() => props.onPre?.()}>
                  上一步
                </Button>,
                <Button type="primary" key="goToTree" onClick={() => props.onSubmit?.()}>
                  下一步 {'>'}
                </Button>,
              ];
            }

            return [
              <Button key="gotoTwo" onClick={() => props.onPre?.()}>
                {'<'} 上一步
              </Button>,
              <Button type="primary" key="goToTree" onClick={() => props.onSubmit?.()}>
                提交 √
              </Button>,
            ];
          },
        }}
      >
        <StepsForm.StepForm<{
          name: string;
        }>
          name="base"
          title="请假信息"
          onFinish={async ({name}) => {
            console.log(name);
            await waitTime(2000);
            return true;
          }}
        >
          <ProFormText
            name="reason"
            label="请假原因"
            width="md"
            tooltip="务必详细"
            placeholder="请输入请假原因"
            rules={[{required: true}]}
          />
          < ProFormRadio.Group
            name="leaveType"
            label="请假类型"
            options={
              [
                {
                  label: '事假',
                  value: 1,
                },
                {
                  label: '病假',
                  value: 2,
                }
                ,
                {
                  label: '公假',
                  value: 3,
                }
                ,
                {
                  label: '申请校外实习',
                  value: 4,
                }
                ,
              ]
            }
            rules={
              [
                {
                  required: true,
                  message: '请假类型是必填项!',
                },
              ]
            }
          />
          <ProFormText
            name="emergencyContact"
            label="紧急联系人姓名"
            width="md"
            tooltip="建议填亲人的姓名"
            placeholder="请输入紧急联系人姓名"
            rules={[{required: true}]}
          />
          <ProFormText
            name="emergencyTelephoneNumber"
            label="紧急联系人电话"
            width="md"
            tooltip="建议填亲人的联系电话"
            placeholder="请输入紧急联系人电话"
            rules={[{required: true}]}
          />
        </StepsForm.StepForm>
        <StepsForm.StepForm<{
          checkbox: string;
        }>
          name="checkbox"
          title="出入信息"
        >
          <ProFormDateTimeRangePicker name="time" label="日期时间区间"/>
          <ProFormText
            name="address"
            label="目的地"
            width="md"
            placeholder="请输入目的地"
            rules={[{required: true}]}
          />
          <ProFormText
            name="traffic"
            label="交通工具  "
            width="md"
            placeholder="请输入交通工具"
            rules={[{required: true}]}
          />
          <ProFormUploadButton
            name="appendix"
            label="附件"
            max={5}
            fieldProps={{
              name: 'file',
              listType: 'picture-card',
            }}
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
        </StepsForm.StepForm>
        <StepsForm.StepForm name="time" title="个人信息">
          <ProFormText
            name="location"
            label="目前所在地"
            width="md"
            placeholder="请输入目前所在地"
            rules={[{required: true}]}
          />
        </StepsForm.StepForm>
      </StepsForm>
    </ProCard>
  );
};
