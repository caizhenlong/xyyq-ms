import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {
  deleteHealthInfo,
  deleteMaterialInfo,
  searchMaterialInfos,
  updateMaterialInfo
} from "@/services/ant-design-pro/api";
import {Image, message} from "antd";


// @ts-ignore
// @ts-ignore
const columns: ProColumns<API.MaterialInfoParams>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '物资类型',
    dataIndex: 'typeName',
    valueType: 'textarea',
  },
  {
    title: '物资名称',
    dataIndex: 'name',
    valueType: 'textarea',
  },
  {
    title: '物资图片',
    dataIndex: 'img',
    render: (_, record) => (
      <div>
        <Image src={record.img} width={50}/>
      </div>
    ),
    search:false,
  },
  {
    title: '物资规格',
    dataIndex: 'specification',
    valueType: 'textarea',
  },
  {
    title: '创建人',
    dataIndex: 'creator',
    valueType: 'textarea',
  },
  {
    title: '备注',
    dataIndex: 'description',
    valueType: 'textarea',
  },
  {
    title: '库存',
    dataIndex: 'total',
    valueType: 'textarea',
  },
  {
    title: '物资单位',
    dataIndex: 'unit',
    valueType: 'textarea',
  },
  {
    title: '是否启用',
    dataIndex: 'status',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '禁用',
        status: 'Error',
      },
      1: {
        text: '启用',
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
    <ProTable<API.MaterialInfoParams>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={searchMaterialInfos}
      editable={{
        type: 'multiple',
        //修改后保存
        onSave: async (rowKey, data, row) => {
          try {
            const result = await updateMaterialInfo(data);
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
            const result = await deleteMaterialInfo(rowKey)
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
