import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {deleteHealthInfo, searchHealths, updateHealthInfo} from "@/services/ant-design-pro/api";
import {message} from "antd";


const columns: ProColumns<API.HealthSmParams>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '学号',
    dataIndex: 'uid',
    valueType: 'textarea',
  },
  {
    title: '姓名',
    dataIndex: 'username',
    valueType: 'textarea',
  },
  {
    title: '身体状况',
    dataIndex: 'physicalCondition',
    valueType: 'select',
    valueEnum: {
      1: {
        text: '正常',
        status: 'Success',
      },
      2: {
        text: '有发热、咳嗽、呼吸困难等症状',
        status: 'Error',
      },
      3: {
        text: '其他症状',
        status: 'Warning',
      },
    },
  },
  {
    title: '现在所在地',
    dataIndex: 'location',
    valueType: 'textarea',
    search:false,
  },
  {
    title: '是否高风险',
    dataIndex: 'isHighRisk',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '否',
        status: 'Error',
      },
      1: {
        text: '是',
        status: 'Success',
      },
    },
  },
  {
    title: '健康码状态',
    dataIndex: 'healthCodeStatus',
    valueType: 'select',
    valueEnum: {
      1: {
        text: '绿码',
        status: 'Success',
      },
      2: {
        text: '黄码',
        status: 'Warning',
      },
      3: {
        text: '红码',
        status: 'Error',
      },
    },
  },
  {
    title: '是否在校  ',
    dataIndex: 'atSchool',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '不在校',
        status: 'Error',
      },
      1: {
        text: '在校',
        status: 'Success',
      },
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateTime',
    search:false
  },

  {
    title: '操作',
    valueType: 'option',
    key: 'option',
    render: (text, record, _, action) => [
      <a
        key="editable"
        onClick={() => {
          // @ts-ignore
          action?.startEditable?.(record.id);
        }}
      >
        编辑
      </a>,
    ],
  },
];

export default () => {
  const actionRef = useRef<ActionType>();

  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  return (
    <ProTable<API.HealthSmParams>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={searchHealths}
      editable={{
        type: 'multiple',
        //修改后保存
        onSave: async (rowKey, data, row) => {
          try {
            const result = await updateHealthInfo(data);
            if (result.code==0) {
              const defaultLoginSuccessMessage = '修改成功！';
              message.success(defaultLoginSuccessMessage);
            }else {
              const defaultLoginFailureMessage = result.description;
              message.error(defaultLoginFailureMessage);
            }
          }catch (error: any) {
            const defaultLoginFailureMessage = '修改失败，请重试！';
            message.error(defaultLoginFailureMessage);
          }
        },
        //删除某一行
        onDelete: async (rowKey)=>{
/*          console.log(rowKey)*/
          try {
            const result = await deleteHealthInfo(rowKey)
            if (result.code==0) {
              const defaultLoginSuccessMessage = '删除成功！';
              message.success(defaultLoginSuccessMessage);
            }else {
              const defaultLoginFailureMessage = result.description;
              message.error(defaultLoginFailureMessage);
            }
          }catch (error: any) {
            const defaultLoginFailureMessage = '删除失败，请重试！';
            message.error(defaultLoginFailureMessage);
          }
        }
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
        onChange(value) {
          console.log('value: ', value);
        },
      }}
      rowKey="id"
      //条件搜索
      search={{
        /*labelWidth: 'auto',*/
        defaultCollapsed: false,
        optionRender: (searchConfig, formProps, dom) => [
          ...dom.reverse(),
        ],
      }}
      /*options={{
        setting: {
          listsHeight: 400,
        },
      }}*/
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
        onChange: (page) => console.log(page),
      }}
      dateFormatter="string"
      headerTitle="用户信息"
      toolBarRender={() => [
      ]}
    />
  );
};
