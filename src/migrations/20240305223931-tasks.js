"use strict";

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable("tasks", {
      id: {
        primaryKey: true,
        autoIncrement: true,
        allowNull: false,
        type: Sequelize.INTEGER
      },
      taskName: {
        type: Sequelize.STRING
      },
      deadline: {
        type: Sequelize.DATE
      },
      description: {
        type: Sequelize.STRING,
        allowNull: true
      },
      completed: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
      }
    });
  },

  async down(queryInterface) {
    await queryInterface.dropTable("tasks");
  }
};
