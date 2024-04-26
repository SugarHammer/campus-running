<template>
  <div class="mod-role">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="dataForm.seatName"
          placeholder="服务名称"
          clearable
        ></el-input>
      </el-form-item><el-form-item>
        <el-input
          v-model="dataForm.roomName"
          placeholder="服务分类名称"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="dataForm.roomState"
          placeholder="服务状态"
          clearable
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()"
          >新增服务</el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;"
    >
      <el-table-column
        prop="seatName"
        header-align="center"
        align="center"
        label="服务名称"
      >
      </el-table-column>
      <el-table-column
        prop="roomName"
        header-align="center"
        align="center"
        label="服务分类"
      >
      </el-table-column>
      <el-table-column header-align="center" align="center" label="服务状态">
        <template slot-scope="scope">
          <div class="table-contents">
            <el-tag :type="!scope.row.roomState ? 'success' : 'danger'">
              {{ !scope.row.roomState ? "开放" : "已预约" }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间"
      >
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="addOrUpdateHandle(scope.row)"
            >修改</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.seatId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
    <add-or-update
      v-if="addOrUpdateVisible"
      ref="addOrUpdate"
      @refreshDataList="getDataList"
      @handCancel="handCancel"
    ></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./seat-add-or-update.vue";
export default {
  name: "SelfStudyIndex",
  components: {
    AddOrUpdate
  },
  data() {
    return {
      dataForm: {
        roomState: "",
        seatName: "",
        roomName:""
      },
      options: [
        {
          value: "",
          label: "全部"
        },
        {
          value: "0",
          label: "开放"
        },
        {
          value: "1",
          label: "已预约"
        }
      ],
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      addOrUpdateVisible: false
    };
  },

  mounted() {},
  activated() {
    this.getDataList();
  },
  methods: {
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/basseat/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          seatName: this.dataForm.seatName,
          roomState: this.dataForm.roomState,
          roomName: this.dataForm.roomName
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.content;
          this.totalPage = Number(data.data.totalCount);
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 删除
    deleteHandle(id) {
      console.log(id);
      this.$confirm(`确定删除此服务?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/basseat/delete"),
            method: "delete",
            data: [id]
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(() => {});
    },
    // 新增
    addOrUpdateHandle(row) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row);
      });
    },
    handCancel() {
      this.addOrUpdateVisible = false;
    }
  }
};
</script>

<style lang="scss" scoped></style>
