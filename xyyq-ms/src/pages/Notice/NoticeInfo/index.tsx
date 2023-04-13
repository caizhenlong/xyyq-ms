import {useModel} from 'umi';
import {List} from "antd";
import moment from "moment";

export default () => {
  {
    const {initialState} = useModel('@@initialState');
    const data: any[] | undefined = [];
    let noticeType = "";
    // @ts-ignore
    if (initialState.currentNotice) {
      initialState.currentNotice.forEach((item) => {
        if (item.type == 0) {
          noticeType = "普通通知";
        }
        if (item.type == 1) {
          noticeType = "讲座通知";
        }
        if (item.type == 2) {
          noticeType = "重要通知";
        }
        item.createTime = moment(item.createTime).format('YYYY-MM-DD HH:mm:ss')
        /*      console.log(item.createTime);*/
        data.push(item.createTime + " " + noticeType + " : " + item.content);
      });
    }
    return <>
      <h1>公告</h1>
      <List
        size="large"
        bordered
        dataSource={data}
        renderItem={(item) => <List.Item>{item}</List.Item>}
      />
    </>
  }
}



