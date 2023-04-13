import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {MaterialOutListInfo} from "@/services/ant-design-pro/api";


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
    title: '物资名称',
    dataIndex: 'name',
    valueType: 'text',
  },
  {
    title: '出库数量',
    dataIndex: 'quantity',
    valueType: 'text',
  },
  {
    title: '出库负责人',
    dataIndex: 'superintendent',
    valueType: 'text',
  },
  {
    title: '出库事由',
    dataIndex: 'reason',
    valueType: 'textarea',
    search:false
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateTime',
    search:false
  },

  /*{
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
  },*/
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
      request={MaterialOutListInfo}
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
